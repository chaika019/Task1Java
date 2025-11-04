package by.chaika19.task1;

import org.junit.jupiter.api.Test;
import by.chaika19.task1.entity.CustomArray;
import by.chaika19.task1.exception.CustomArrayException;
import by.chaika19.task1.service.stream.CustomArrayCalculatorStream;
import by.chaika19.task1.service.stream.impl.CustomArrayCalculatorStreamImpl;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayCalculatorStreamTest {
    private final CustomArrayCalculatorStream calculator = new CustomArrayCalculatorStreamImpl();
    private final CustomArray testArray = CustomArray.builder()
            .withArray(new int[]{1,2,3,4,-5,16,7})
            .build();;
    private final CustomArray emptyArray = CustomArray.builder()
            .withArray(new int[]{})
            .build();

    @Test
    public void findMin() throws CustomArrayException {
        int min = calculator.findMin(testArray);
        assertEquals(-5,min);
    }

    @Test
    public void calculator_findMax() throws CustomArrayException {
        int max = calculator.findMax(testArray);
        assertEquals(16, max, "max should be 16.");
    }

    @Test
    public void calculator_replaceElements() throws CustomArrayException {
        CustomArray expected = CustomArray.builder()
                .withArray(new int[]{1, 10, 3, 4, -5, 16, 7})
                .build();
        CustomArray actual = calculator.replaceElements(testArray, 2, 10);
        assertEquals(expected, actual);
    }

    @Test
    public void calculator_Sum() throws CustomArrayException {
        assertEquals(28, calculator.calculateSum(testArray));
    }

    @Test
    public void calculator_SumWithNullValue(){
        assertThrows(CustomArrayException.class, () -> calculator.calculateSum(null));
    }

    @Test
    public void calculator_SumWithEmptyArray() {
        assertThrows(CustomArrayException.class, () -> calculator.calculateSum(emptyArray));
    }

    @Test
    public void calculator_Average() throws CustomArrayException {
        assertEquals(4, calculator.calculateAverage(testArray));
    }

    @Test
    public void calculator_Positives() throws CustomArrayException {
        assertEquals(6, calculator.countPositives(testArray));
    }

    @Test
    public void calculator_Negatives() throws CustomArrayException {
        assertEquals(1, calculator.countNegatives(testArray));
    }
}
