package by.chaika19.task1.reader.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.chaika19.task1.exception.CustomArrayException;
import by.chaika19.task1.reader.CustomArrayFileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CustomArrayFileReaderImpl implements CustomArrayFileReader {
    private static final Logger logger = LogManager.getLogger(CustomArrayFileReaderImpl.class);

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