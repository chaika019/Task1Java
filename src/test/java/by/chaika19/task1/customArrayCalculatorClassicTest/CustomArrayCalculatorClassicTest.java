package by.chaika19.task1.customArrayCalculatorClassicTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import by.chaika19.task1.entity.CustomArray;
import by.chaika19.task1.exception.CustomArrayException;
import by.chaika19.task1.service.classic.CustomArrayCalculator;
import by.chaika19.task1.service.classic.impl.CustomArrayCalculatorImpl;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayCalculatorClassicTest {

    private CustomArrayCalculator calculator;
    private CustomArray testArray;
    private CustomArray emptyArray;

    @BeforeEach
    public void setUp() {
        calculator = new CustomArrayCalculatorImpl();
        testArray = CustomArray.builder()
                .withArray(new int[]{1,2,3,4,-5,6,7})
                .build();
        emptyArray = CustomArray.builder()
                .withArray(new int[]{})
                .build();
    }

    @Test
    public void calculator_findMin() throws CustomArrayException {
        int min = calculator.findMin(testArray);
        System.out.println(min);
        assertEquals(-5, min, "min should be -5.");
    }

    @Test
    public void calculator_findMax() throws CustomArrayException {
        int max = calculator.findMax(testArray);
        System.out.println(max);
        assertEquals(7, max, "max should be 7.");
    }

    @Test
    public void calculator_replaceElements() throws CustomArrayException {
        CustomArray expected = CustomArray.builder()
                .withArray(new int[]{1,2,3,4,-5,6,52})
                .build();
        CustomArray result = calculator.replaceElements(testArray, 7, 52);
        System.out.println(result);
        assertEquals(expected, result);
    }

    @Test
    public void calculator_findSum() throws CustomArrayException {
        int result = calculator.calculateSum(testArray);
        System.out.println(result);
        assertEquals(18, result, "sum should be 18.");
    }

    @Test
    public void calculator_findAverage() throws CustomArrayException {
        double avg = calculator.calculateAverage(testArray);
        System.out.println(avg);
        assertEquals(2.5714285714285716, avg, "average should be 5.");
    }

    @Test
    public void calculator_countPositives() throws CustomArrayException {
        int result = calculator.countPositives(testArray);
        System.out.println(result);
        assertEquals(6, result, "count should be 6.");
    }

    @Test
    public void calculator_countNegatives() throws CustomArrayException {
        int result = calculator.countNegatives(testArray);
        System.out.println(result);
        assertEquals(1, result, "count should be 1.");
    }

    //In fact, our validator doesn't allow empty or null arrays,
    //but I'll add it just in case
    @Test
    public void calculator_emptyArray() {
        assertThrows(CustomArrayException.class, () -> calculator.countNegatives(emptyArray));
    }
}
