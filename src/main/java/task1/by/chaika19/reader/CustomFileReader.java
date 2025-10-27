package task1.by.chaika19.reader;

import task1.by.chaika19.exception.CustomArrayException;

import java.util.List;

public interface CustomFileReader {
    List<String> readFile(String path) throws CustomArrayException;
}
