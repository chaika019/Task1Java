package task1.by.chaika19;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task1.by.chaika19.comparator.SortByAverageComparator;
import task1.by.chaika19.comparator.SortByMaxComparator;
import task1.by.chaika19.comparator.SortBySumComparator;
import task1.by.chaika19.entity.CustomArray;
import task1.by.chaika19.exception.CustomArrayException;
import task1.by.chaika19.parser.CustomArrayDataParser;
import task1.by.chaika19.parser.impl.CustomArrayDataParserImpl;
import task1.by.chaika19.reader.CustomArrayFileReader;
import task1.by.chaika19.reader.impl.CustomArrayFileReaderImpl;
import task1.by.chaika19.repository.CustomArrayRepository;
import task1.by.chaika19.specification.ByIdSpecification;
import task1.by.chaika19.specification.ByMaxSpecification;
import task1.by.chaika19.specification.BySumSpecification;
import task1.by.chaika19.validator.ArrayDataValidator;
import task1.by.chaika19.validator.impl.ArrayDataValidatorImpl;

import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String DATA_FILE_PATH = "src/main/resources/data/data.txt";

    public static void main(String[] args) {
        CustomArrayRepository repository = CustomArrayRepository.getInstance();

        logger.info("Loading data from a file and populating the repository");

        CustomArrayFileReader fileReader = new CustomArrayFileReaderImpl();
        ArrayDataValidator validator = new ArrayDataValidatorImpl();
        CustomArrayDataParser parser = new CustomArrayDataParserImpl();

        List<String> lines;
        try {
            lines = fileReader.readFile(DATA_FILE_PATH);
        } catch (CustomArrayException e) {
            logger.error("Critical error when reading a file {}: {}", DATA_FILE_PATH, e.getMessage());
            return;
        }

        int successfulLoads = 0;
        for (String line : lines) {
            if (validator.isValidArrayLine(line)) {
                try {
                    int[] data = parser.parseLine(line);

                    if (data.length > 0) {
                        CustomArray array = CustomArray.builder().withArray(data).build();
                        repository.add(array);
                        successfulLoads++;
                    }
                } catch (CustomArrayException e) {
                    logger.warn("Skipping a line due to a parsing error: \"{}\". Error: {}", line, e.getMessage());
                }
            } else {
                logger.warn("Line skipping: Incorrect data format: \"{}\"", line);
            }
        }

        if (successfulLoads == 0) {
            logger.error("Failed to load any valid arrays from the file");
            return;
        }

        logger.info("Uploaded successfully {} arrays", successfulLoads);
        repository.getAll().forEach(logger::info);

        String idA = repository.getAll().stream().findFirst().map(CustomArray::getId).orElse("no-id-found");

        CustomArray arrayA = repository.get(idA).orElse(null);
        if (arrayA == null) {
            logger.error("Couldn't find an array for testing the Observer");
            return;
        }

        logger.info("Testing Observer (Array A)");

        try {
            arrayA.setElement(0, 150);
        } catch (CustomArrayException e) {
            logger.error("Error when changing an element: {}", e.getMessage());
        }

        logger.info("Statistics for Array A after the change: {}",
                repository.get(idA).flatMap(a -> repository.getWarehouse().getStatistics(a.getId()))
                        .map(s -> String.format("Sum: %d, Max: %d", s.getSum(), s.getMax()))
                        .orElse("Statistics not found"));

        logger.info("Searching Arrays by Specification");

        BySumSpecification sumSpec = new BySumSpecification(20, 40);
        List<CustomArray> sumResults = repository.query(sumSpec);
        logger.info("Search results for Sum (100-500): Found {} arrays.", sumResults.size());

        ByMaxSpecification maxSpec = new ByMaxSpecification(Integer.MIN_VALUE, 10);
        List<CustomArray> maxResults = repository.query(maxSpec);
        logger.info("Search results for Max (<10): Found {} arrays", maxResults.size());

        ByIdSpecification idSpec = new ByIdSpecification(idA);
        List<CustomArray> idResult = repository.query(idSpec);
        logger.info("Search results by ID: {}", idResult.size() > 0 ? "Found" : "Not found");

        logger.info("Sort Arrays by Specification");

        List<CustomArray> sortedBySum = repository.sort(new SortBySumComparator());
        logger.info("Sort by Sum (Min -> Max): {}", sortedBySum.stream()
                .map(a -> repository.getWarehouse().getStatistics(a.getId()).map(s -> "Sum=" + s.getSum()).orElse("?"))
                .toList());

        List<CustomArray> sortedByMax = repository.sort(new SortByMaxComparator());
        logger.info("Sort by Max (Min -> Max): {}", sortedByMax.stream()
                .map(a -> repository.getWarehouse().getStatistics(a.getId()).map(s -> "Max=" + s.getMax()).orElse("?"))
                .toList());

        List<CustomArray> sortedByAverage = repository.sort(new SortByAverageComparator());
        logger.info("Sort by Avg (Min -> Max): {}", sortedByAverage.stream()
                .map(a -> repository.getWarehouse().getStatistics(a.getId()).map(s -> "Avg=" + String.format("%.2f", s.getAverage())).orElse("?"))
                .toList());
    }
}
