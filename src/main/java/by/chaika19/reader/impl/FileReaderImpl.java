package by.chaika19.reader.impl;

import by.chaika19.exception.FileReadException;
import by.chaika19.reader.FileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReaderImpl implements FileReader {
    @Override
    public List<String> readFile(String path) throws FileReadException {
        try {
            Path filePath = Paths.get(path);
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new FileReadException("Не удалось прочитать файл: " + e.getMessage());
        }
    }
}