package by.chaika19.task1.service.classic;

import by.chaika19.task1.entity.CustomArray;
import by.chaika19.task1.exception.CustomArrayException;

public interface CustomArrayCalculator {
    int findMin(CustomArray array) throws CustomArrayException;
    int findMax(CustomArray array) throws CustomArrayException;
    CustomArray replaceElements(CustomArray array, int conditionValue, int replacementValue) throws CustomArrayException;
    double calculateAverage(CustomArray array) throws CustomArrayException;
    int calculateSum(CustomArray array) throws CustomArrayException;
    int countPositives(CustomArray array) throws CustomArrayException;
    int countNegatives(CustomArray array) throws CustomArrayException;
}
