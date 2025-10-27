package test.array.CustomArrayCalculatorClassicTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task1.by.chaika19.entity.CustomArray;
import task1.by.chaika19.exception.CustomArrayException;
import task1.by.chaika19.service.classic.CustomArrayCalculator;
import task1.by.chaika19.service.classic.impl.CustomArrayCalculatorImpl;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayCalculatorClassicTest {

    private CustomArrayCalculator calculator;
    private CustomArray testArray;
    private CustomArray emptyArray;
    private static final double DELTA = 0.0001;

    @BeforeEach
    void setUp() {
        calculator = new CustomArrayCalculatorImpl();
        testArray = CustomArray.builder().withArray(new int[]{5, -2, 10, 0, -8, 3}).build();
        emptyArray = CustomArray.builder().withArray(new int[]{}).build();
    }

    // --- Min/Max Tests ---

    @Test
    void findMinShouldReturnCorrectValue() throws CustomArrayException {
        int expected = -8;
        int actual = calculator.findMin(testArray);
        assertEquals(expected, actual, "Минимальное значение найдено неверно.");
    }

    @Test
    void findMaxShouldReturnCorrectValue() throws CustomArrayException {
        int expected = 10;
        int actual = calculator.findMax(testArray);
        assertEquals(expected, actual, "Максимальное значение найдено неверно.");
    }

    // --- Sum/Average Tests ---

    @Test
    void calculateSumShouldReturnCorrectTotal() throws CustomArrayException {
        // 5 + (-2) + 10 + 0 + (-8) + 3 = 8
        int expected = 8;
        int actual = calculator.calculateSum(testArray);
        assertEquals(expected, actual, "Сумма рассчитана неверно.");
    }

    @Test
    void calculateAverageShouldReturnCorrectValue() throws CustomArrayException {
        // Сумма = 8, Длина = 6. Среднее = 8.0 / 6 = 1.33333...
        double expected = 8.0 / 6.0;
        double actual = calculator.calculateAverage(testArray);
        assertEquals(expected, actual, DELTA, "Среднее значение рассчитано неверно.");
    }

    // --- Count Tests ---

    @Test
    void countPositivesShouldReturnCorrectCount() throws CustomArrayException {
        // Положительные: 5, 10, 3 (3 элемента)
        int expected = 3;
        int actual = calculator.countPositives(testArray);
        assertEquals(expected, actual, "Количество положительных чисел посчитано неверно.");
    }

    @Test
    void countNegativesShouldReturnCorrectCount() throws CustomArrayException {
        // Отрицательные: -2, -8 (2 элемента)
        int expected = 2;
        int actual = calculator.countNegatives(testArray);
        assertEquals(expected, actual, "Количество отрицательных чисел посчитано неверно.");
    }

    // --- Replace Test ---

    @Test
    void replaceElementsShouldModifyAndReturnNewArray() throws CustomArrayException {
        // Заменяем -8 на 99
        CustomArray result = calculator.replaceElements(testArray, -8, 99);
        CustomArray expectedArray = CustomArray.builder().withArray(new int[]{5, -2, 10, 0, 99, 3}).build();

        assertEquals(expectedArray, result, "Замена элемента выполнена неверно.");
        assertNotSame(testArray, result, "Метод должен возвращать новый объект CustomArray.");
    }

    // --- Exception Tests ---

    @Test
    void calculatorMethodsShouldThrowExceptionIfEmptyArray() {
        assertThrows(CustomArrayException.class, () -> calculator.findMin(emptyArray));
        assertThrows(CustomArrayException.class, () -> calculator.calculateSum(emptyArray));
    }
}
