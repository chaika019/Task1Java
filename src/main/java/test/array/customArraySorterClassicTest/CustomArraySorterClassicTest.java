package test.array.customArraySorterClassicTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task1.by.chaika19.entity.CustomArray;
import task1.by.chaika19.sorter.classic.CustomArraySorter;
import task1.by.chaika19.sorter.classic.impl.CustomArraySorterImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class CustomArraySorterClassicTest {
    private CustomArraySorter sorter;
    private CustomArray unsortedArray;
    private CustomArray sortedArray;
    private CustomArray emptyArray;
    private CustomArray expectedArray;

    @BeforeEach
    public void setUp() {
        sorter = new CustomArraySorterImpl();
        unsortedArray = CustomArray.builder().withArray(new int[]{5, -2, 10, 0, -8, 3}).build();
        sortedArray = CustomArray.builder().withArray(new int[]{-8, -2, 0, 3, 5, 10}).build();
        emptyArray = CustomArray.builder().withArray(new int[]{}).build();
        expectedArray = CustomArray.builder().withArray(new int[]{-8, -2, 0, 3, 5, 10}).build();
    }

    @Test
    void bubbleSort_shouldReturnSorted() {
        CustomArray result = sorter.bubbleSort(unsortedArray);
        assertEquals(expectedArray, result, "Bubble Sort failed to sort the unsorted array.");
        assertNotSame(unsortedArray, result, "Bubble Sort should return a new CustomArray object.");
    }

    @Test
    void bubbleSort_shouldReturnSameElements() {
        CustomArray result = sorter.bubbleSort(sortedArray);
        assertEquals(sortedArray, result, "Bubble Sort failed on an already sorted array.");
    }

    @Test
    void bubbleSort_shouldReturnEmptyArray() {
        CustomArray result = sorter.bubbleSort(emptyArray);
        assertEquals(emptyArray, result, "Bubble Sort failed to handle an empty array.");
    }

    @Test
    void sortInsertion_shouldReturnSorted() {
        CustomArray result = sorter.sortInsertion(unsortedArray);
        assertEquals(expectedArray, result, "Insertion Sort failed to sort the unsorted array.");
        assertNotSame(unsortedArray, result, "Insertion Sort should return a new CustomArray object.");
    }

    @Test
    void sortInsertion_shouldReturnSameElements() {
        CustomArray result = sorter.sortInsertion(sortedArray);
        assertEquals(sortedArray, result, "Insertion Sort failed on an already sorted array.");
    }

    @Test
    void sortQuick_shouldReturnSorted() {
        CustomArray result = sorter.sortQuick(unsortedArray);
        assertEquals(expectedArray, result, "Quick Sort failed to sort the unsorted array.");
        assertNotSame(unsortedArray, result, "Quick Sort should return a new CustomArray object.");
    }

    @Test
    void sortQuick_shouldReturnEmptyArray() {
        CustomArray result = sorter.sortQuick(emptyArray);
        assertEquals(emptyArray, result, "Quick Sort failed to handle an empty array.");
    }
}
