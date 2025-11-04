package by.chaika19.task1.sorter.stream.impl;

import by.chaika19.task1.entity.CustomArray;
import by.chaika19.task1.exception.CustomArrayException;
import by.chaika19.task1.sorter.stream.CustomArraySorterStream;

import java.util.stream.IntStream;

public class CustomArraySorterStreamImpl implements CustomArraySorterStream {

    private void validateArray(CustomArray array) throws CustomArrayException {
        if (array == null || array.getArray().length == 0) {
            throw new CustomArrayException("Array must not be null or empty for sorting.");
        }
    }

    @Override
    public CustomArray streamSort(CustomArray array) throws CustomArrayException {
        validateArray(array);

        int[] sortedArr = IntStream.of(array.getArray())
                .sorted()
                .toArray();

        return CustomArray.builder()
                .withArray(sortedArr)
                .build();
    }
}
