package by.chaika19.task1.service.stream;

import by.chaika19.task1.entity.CustomArray;
import by.chaika19.task1.exception.CustomArrayException;

public interface CustomArrayCalculatorStream {
    int findMin(CustomArray array) throws CustomArrayException;
    int findMax(CustomArray array) throws CustomArrayException;
    CustomArray replaceElements(CustomArray array, int conditionValue, int replacementValue) throws CustomArrayException;
    int calculateSum(CustomArray array) throws CustomArrayException;
    double calculateAverage(CustomArray array) throws CustomArrayException;
    long countPositives(CustomArray array) throws CustomArrayException;
    long countNegatives(CustomArray array) throws CustomArrayException;
}
