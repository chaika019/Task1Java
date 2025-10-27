package task1.by.chaika19.service.classic.impl;

import task1.by.chaika19.entity.CustomArray;
import task1.by.chaika19.exception.CustomArrayException;
import task1.by.chaika19.service.classic.CustomArrayCalculator;

public class CustomArrayCalculatorImpl implements CustomArrayCalculator {
    private void validateArray(CustomArray array) throws CustomArrayException {
        if (array == null || array.getArray().length == 0) {
            throw new CustomArrayException("Array must not be null or empty.");
        }
    }

    @Override
    public int findMin(CustomArray array) throws CustomArrayException {
        validateArray(array);
        int[] arr = array.getArray();
        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    @Override
    public int findMax(CustomArray array) throws CustomArrayException {
        validateArray(array);
        int[] arr = array.getArray();
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    @Override
    public CustomArray replaceElements(CustomArray array, int conditionValue, int replacementValue) throws CustomArrayException {
        validateArray(array);

        int[] originalArr = array.getArray();
        int[] newArr = originalArr.clone();

        for (int i = 0; i < newArr.length; i++) {
            if (newArr[i] == conditionValue) {
                newArr[i] = replacementValue;
            }
        }

        return CustomArray.builder().withArray(newArr).build();
    }

    @Override
    public int calculateSum(CustomArray array) throws CustomArrayException {
        validateArray(array);
        int[] arr = array.getArray();
        int sum = 0;

        for (int element : arr) {
            sum += element;
        }
        return sum;
    }

    @Override
    public double calculateAverage(CustomArray array) throws CustomArrayException {
        validateArray(array);

        int sum = calculateSum(array);
        int length = array.getArray().length;

        return (double) sum / length;
    }

    @Override
    public int countPositives(CustomArray array) throws CustomArrayException {
        validateArray(array);
        int[] arr = array.getArray();
        int count = 0;

        for (int element : arr) {
            if (element > 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countNegatives(CustomArray array) throws CustomArrayException {
        validateArray(array);
        int[] arr = array.getArray();
        int count = 0;

        for (int element : arr) {
            if (element < 0) {
                count++;
            }
        }
        return count;
    }
}