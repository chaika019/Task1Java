package by.chaika19.validator.impl;

import by.chaika19.validator.ArrayDataValidator;

public class ArrayDataValidatorImpl implements ArrayDataValidator {
    private static final String VALIDATION_REGEX = "^\\s*\\d+\\s*([,\\s]\\s*\\d+\\s*)*$";

    @Override
    public boolean isValid(String line) {
        if (line == null || line.trim().isEmpty()) {
            return false;
        }
        return line.matches(VALIDATION_REGEX);
    }
}
