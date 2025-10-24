package by.chaika19.reader;

import by.chaika19.exception.FileReadException;

import java.util.List;

public interface FileReader {
    List<String> readFile(String path) throws FileReadException;
}
