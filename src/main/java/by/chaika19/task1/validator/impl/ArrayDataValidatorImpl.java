package by.chaika19.task1.validator.impl;

import by.chaika19.task1.validator.ArrayDataValidator;

public class ArrayDataValidatorImpl implements ArrayDataValidator {
    @Override
    public boolean isValidArrayLine(String line) {
        if (line != null && !line.isBlank()) {
            return line.matches(ARRAY_VALIDATION_REGEX);
        } else {
            return false;
        }
    }
}
