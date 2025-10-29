package test.array.customArraySorterStreamTest;

import org.junit.jupiter.api.Test;
import task1.by.chaika19.entity.CustomArray;
import task1.by.chaika19.exception.CustomArrayException;
import task1.by.chaika19.sorter.stream.CustomArraySorterStream;
import task1.by.chaika19.sorter.stream.impl.CustomArraySorterStreamImpl;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArraySorterStreamTest {

    private final CustomArraySorterStream sorter = new CustomArraySorterStreamImpl();
    private final CustomArray unsortedArray = CustomArray.builder().withArray(new int[]{5, -2, 10, 0, -8, 3}).build();
    private final CustomArray emptyArray = CustomArray.builder().withArray(new int[]{}).build();
    private final CustomArray expectedArray = CustomArray.builder().withArray(new int[]{-8, -2, 0, 3, 5, 10}).build();


    @Test
    void streamSort_UnsortedArray_ShouldReturnSorted() throws CustomArrayException {
        CustomArray result = sorter.streamSort(unsortedArray);
        assertEquals(expectedArray, result, "Stream Sort failed to sort the array.");
        assertNotSame(unsortedArray, result, "Stream Sort should return a new CustomArray object.");
    }

    @Test
    void streamSort_ShouldThrowExceptionIfEmptyArray() {
        assertThrows(CustomArrayException.class, () -> sorter.streamSort(emptyArray));
    }

    @Test
    void streamSort_ShouldThrowExceptionIfNullArray() {
        assertThrows(CustomArrayException.class, () -> sorter.streamSort(null));
    }
}
