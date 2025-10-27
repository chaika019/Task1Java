package task1.by.chaika19.service.classic;

import task1.by.chaika19.entity.CustomArray;
import task1.by.chaika19.exception.CustomArrayException;

public interface CustomArrayCalculator {
    int findMin(CustomArray array) throws CustomArrayException;
    int findMax(CustomArray array) throws CustomArrayException;

    CustomArray replaceElements(CustomArray array, int conditionValue, int replacementValue) throws CustomArrayException;

    double calculateAverage(CustomArray array) throws CustomArrayException;

    int calculateSum(CustomArray array) throws CustomArrayException;
    int countPositives(CustomArray array) throws CustomArrayException;
    int countNegatives(CustomArray array) throws CustomArrayException;
}
