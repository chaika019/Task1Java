package task1.by.chaika19.reader.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task1.by.chaika19.exception.CustomArrayException;
import task1.by.chaika19.reader.CustomFileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArrayFileReaderImpl implements CustomFileReader {
    private static final Logger logger = LogManager.getLogger(ArrayFileReaderImpl.class);

    @Override
    public List<String> readFile(String path) throws CustomArrayException {
        List<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(lines::add);
        } catch (IOException e) {
            logger.error("Error reading the file in {}", path, e);
            throw new CustomArrayException("Error reading the file in " + path, e);
        }
        return lines;
    }
}