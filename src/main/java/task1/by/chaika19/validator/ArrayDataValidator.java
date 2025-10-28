package task1.by.chaika19.validator;

public interface ArrayDataValidator {
    boolean isValidArrayLine(String line);
    String ARRAY_VALIDATION_REGEX = "^[\\s,]*(-?\\d+)([\\s,]+-?\\d+)*[\\s,]*$";
}