package task1.by.chaika19.validator.impl;

import task1.by.chaika19.validator.ArrayDataValidator;

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
