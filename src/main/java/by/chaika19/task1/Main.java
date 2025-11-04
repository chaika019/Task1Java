package by.chaika19.task1;

import by.chaika19.task1.warehouse.CustomArrayWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.chaika19.task1.comparator.SortByAverageComparator;
import by.chaika19.task1.comparator.SortByMaxComparator;
import by.chaika19.task1.comparator.SortBySumComparator;
import by.chaika19.task1.entity.CustomArray;
import by.chaika19.task1.exception.CustomArrayException;
import by.chaika19.task1.parser.CustomArrayDataParser;
import by.chaika19.task1.parser.impl.CustomArrayDataParserImpl;
import by.chaika19.task1.reader.CustomArrayFileReader;
import by.chaika19.task1.reader.impl.CustomArrayFileReaderImpl;
import by.chaika19.task1.repository.CustomArrayRepository;
import by.chaika19.task1.specification.impl.ByIdSpecification;
import by.chaika19.task1.specification.impl.ByMaxSpecification;
import by.chaika19.task1.specification.impl.BySumSpecification;
import by.chaika19.task1.validator.ArrayDataValidator;
import by.chaika19.task1.validator.impl.ArrayDataValidatorImpl;

import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String DATA_FILE_PATH = "src/main/resources/data/data.txt";

    public static void main(String[] args) {
        CustomArrayRepository repository = CustomArrayRepository.getInstance();
        CustomArrayWarehouse warehouse = CustomArrayWarehouse.getInstance();

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
                warehouse.getStatistics(idA)
                        .map(s -> String.format("Sum: %d, Max: %d", s.getSum(), s.getMax()))
                        .orElse("Statistics not found"));

        logger.info("Searching Arrays by Specification");

        BySumSpecification sumSpec = new BySumSpecification(10, 80);
        List<CustomArray> sumResults = repository.query(sumSpec);
        logger.info("Search results for Sum (20-40): Found {} arrays.", sumResults.size());

        ByMaxSpecification maxSpec = new ByMaxSpecification(Integer.MIN_VALUE, 10);
        List<CustomArray> maxResults = repository.query(maxSpec);
        logger.info("Search results for Max (<10): Found {} arrays", maxResults.size());

        ByIdSpecification idSpec = new ByIdSpecification(idA);
        List<CustomArray> idResult = repository.query(idSpec);
        logger.info("Search results by ID: {}", !idResult.isEmpty() ? "Found" : "Not found");

        logger.info("Sort Arrays by Specification");

        List<CustomArray> sortedBySum = repository.sort(new SortBySumComparator());
        logger.info("Sort by Sum (Min -> Max): {}", sortedBySum.stream()
                .map(a -> warehouse.getStatistics(a.getId()).map(s -> "Sum=" + s.getSum()).orElse("?"))
                .toList());

        List<CustomArray> sortedByMax = repository.sort(new SortByMaxComparator());
        logger.info("Sort by Max (Min -> Max): {}", sortedByMax.stream()
                .map(a -> warehouse.getStatistics(a.getId()).map(s -> "Max=" + s.getMax()).orElse("?"))
                .toList());

        List<CustomArray> sortedByAverage = repository.sort(new SortByAverageComparator());
        logger.info("Sort by Avg (Min -> Max): {}", sortedByAverage.stream()
                .map(a -> warehouse.getStatistics(a.getId()).map(s -> "Avg=" + String.format("%.2f", s.getAverage())).orElse("?"))
                .toList());
    }
}
