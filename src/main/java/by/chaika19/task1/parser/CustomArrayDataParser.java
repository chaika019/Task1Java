package by.chaika19.task1.parser;

import by.chaika19.task1.exception.CustomArrayException;

public interface CustomArrayDataParser {
    int[] parseLine(String line) throws CustomArrayException;
    String SPACE_DELIMITER_REGEX = "[\\s,]+";
}
