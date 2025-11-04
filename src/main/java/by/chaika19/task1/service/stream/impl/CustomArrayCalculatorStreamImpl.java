package by.chaika19.task1.service.stream.impl;

import by.chaika19.task1.service.stream.CustomArrayCalculatorStream;
import by.chaika19.task1.entity.CustomArray;
import by.chaika19.task1.exception.CustomArrayException;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class CustomArrayCalculatorStreamImpl implements CustomArrayCalculatorStream {

    @Override
    public int findMin(CustomArray array) throws CustomArrayException {
        if (array == null) {
            throw new CustomArrayException("CustomArray object must not be null.");
        }

        OptionalInt min = IntStream.of(array.getArray()).min();
        return min.orElseThrow(() -> new CustomArrayException("Cannot find minimum value in an empty array."));
    }

    @Override
    public int findMax(CustomArray array) throws CustomArrayException {
        if (array == null) {
            throw new CustomArrayException("CustomArray object must not be null.");
        }

        OptionalInt max = IntStream.of(array.getArray()).max();
        return max.orElseThrow(() -> new CustomArrayException("Cannot find maximum value in an empty array."));
    }

    @Override
    public CustomArray replaceElements(CustomArray array, int conditionValue, int replacementValue) throws CustomArrayException {
        if (array == null) {
            throw new CustomArrayException("CustomArray object must not be null.");
        }

        int[] newArrayData = IntStream.of(array.getArray())
                .map(element -> element == conditionValue ? replacementValue : element)
                .toArray();

        return CustomArray.builder()
                .withArray(newArrayData)
                .build();
    }

    @Override
    public int calculateSum(CustomArray array) throws CustomArrayException {
        if (array == null) {
            throw new CustomArrayException("CustomArray object must not be null.");
        }

        OptionalInt sum = IntStream.of(array.getArray()).reduce(Integer::sum);
        return sum.orElseThrow(() -> new CustomArrayException("Cannot calculate sum for an empty array."));
    }

    @Override
    public double calculateAverage(CustomArray array) throws CustomArrayException {
        if (array == null) {
            throw new CustomArrayException("CustomArray object must not be null.");
        }

        OptionalDouble average = IntStream.of(array.getArray()).average();
        return average.orElseThrow(() -> new CustomArrayException("Cannot calculate average for an empty array."));
    }

    public long countPositives(CustomArray array) throws CustomArrayException {
        if (array == null) {
            throw new CustomArrayException("CustomArray object must not be null.");
        }

        return IntStream.of(array.getArray())
                .filter(element -> element > 0)
                .count();
    }

    public long countNegatives(CustomArray array) throws CustomArrayException {
        if (array == null) {
            throw new CustomArrayException("CustomArray object must not be null.");
        }

        return IntStream.of(array.getArray())
                .filter(element -> element < 0)
                .count();
    }
}
