package task1.by.chaika19.validator.impl;

import task1.by.chaika19.validator.ArrayDataValidator;

public class ArrayDataValidatorImpl implements ArrayDataValidator {
    private static final String ARRAY_VALIDATION_REGEX = "^\\s*(-?\\d+)(\\s+-?\\d+)*\\s*$";

    @Override
    public boolean isValidArrayLine(String line) {
        if (line != null && !line.isBlank()) {
            return line.matches(ARRAY_VALIDATION_REGEX);
        } else {
            return false;
        }
    }
}
