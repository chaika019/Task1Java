package by.chaika19.task1.reader;

import by.chaika19.task1.exception.CustomArrayException;

import java.util.List;

public interface CustomArrayFileReader {
    List<String> readFile(String path) throws CustomArrayException;
}
