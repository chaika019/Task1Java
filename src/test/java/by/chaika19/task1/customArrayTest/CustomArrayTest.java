package by.chaika19.task1.customArrayTest;

import org.junit.jupiter.api.Test;
import by.chaika19.task1.entity.CustomArray;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayTest {

    @Test
    void builder_givenValidValues() {
        int[] data = {1, 2, 3, -4};
        CustomArray array = CustomArray.builder()
                .withArray(data)
                .build();

        assertNotNull(array);
        assertArrayEquals(data, array.getArray());
    }

    @Test
    void builder_givenNullValue() {
        CustomArray array = CustomArray.builder()
                .withArray(null)
                .build();

        assertNotNull(array);
        assertArrayEquals(new int[0], array.getArray());
    }

    @Test
    void getArray_shouldReturnCopy() {
        int[] originalData = {123, 21, 1231};
        CustomArray customArray = CustomArray.builder()
                .withArray(originalData)
                .build();

        int[] customArray2 = customArray.getArray();

        assertNotSame(originalData, customArray2, "getArray should return a new object instead of the original one.");

        customArray2[0] = 1;
        int[] internalState = customArray.getArray();
        assertEquals(123, internalState[0], "Changing the returned array should not change the internal state of the CustomArray.");
    }

    @Test
    void equals_ShouldReturnTrue_forIdenticalContent() {
        CustomArray array1 = CustomArray.builder()
                .withArray(new int[]{1, 2, 3})
                .build();
        CustomArray array2 = CustomArray.builder()
                .withArray(new int[]{1, 2, 3})
                .build();

        assertTrue(array1.equals(array1));
        assertTrue(array1.equals(array2));
    }

    @Test
    void equals_ShouldReturnFalse_ForDifferentContentAndTypes() {
        CustomArray array1 = CustomArray.builder()
                .withArray(new int[]{1, 2, 3})
                .build();
        CustomArray array2 = CustomArray.builder()
                .withArray(new int[]{1, 2, 4})
                .build();
        CustomArray array3 = CustomArray.builder()
                .withArray(new int[]{1, 2})
                .build();

        assertFalse(array1.equals(array2));
        assertFalse(array1.equals(array3));
        assertFalse(array1.equals(null));
        assertFalse(array1.equals("Some string"));
    }

    @Test
    void hashCode_ShouldReturnSameValue_ForEqualObjects() {
        CustomArray array1 = CustomArray.builder()
                .withArray(new int[]{10, 20, 30})
                .build();
        CustomArray array2 = CustomArray.builder()
                .withArray(new int[]{10, 20, 30})
                .build();

        assertEquals(array1.hashCode(), array2.hashCode());
    }

    @Test
    void toString_ShouldReturnString() {
        String id = "eb431034-b55b-4c06-bb40-f3cd47361b1d";
        int[] data = {1, -2, 3};
        CustomArray array = CustomArray.builder()
                .withId(id)
                .withArray(data)
                .build();
        String expected = "CustomArray [id=eb431034-b55b-4c06-bb40-f3cd47361b1d, array=[1, -2, 3]]";

        assertEquals(expected, array.toString());
    }
}
