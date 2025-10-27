package task1.by.chaika19.parser;

import task1.by.chaika19.exception.CustomArrayException;

public interface CustomArrayDataParser {
    int[] parseLine(String line) throws CustomArrayException;
}
