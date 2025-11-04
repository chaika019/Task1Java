package by.chaika19.task1.customArrayValidatorTest;

import org.junit.jupiter.api.Test;
import by.chaika19.task1.validator.ArrayDataValidator;
import by.chaika19.task1.validator.impl.ArrayDataValidatorImpl;

public class CustomArrayValidatorTest {
    private final ArrayDataValidator validator = new ArrayDataValidatorImpl();

    @Test
    void isValidArrayLine_One() {
        System.out.println(validator.isValidArrayLine("1 2 3 456"));
    }

    @Test
    void isValidArrayLine_Two() {
        System.out.println(validator.isValidArrayLine("10 20 -30 40"));
    }

    @Test
    void isValidArrayLine_Three() {
        System.out.println(validator.isValidArrayLine("0 0 1 -2"));
    }

    @Test
    void isValidArrayLine_Four() {
        System.out.println(validator.isValidArrayLine("  1 2 3  "));
    }

    @Test
    void isValidArrayLine_Five() {
        System.out.println(validator.isValidArrayLine("1a 2"));
    }

    @Test
    void isValidArrayLine_Six() {
        System.out.println(validator.isValidArrayLine(""));
    }

    @Test
    void isValidArrayLine_Seven() {
        System.out.println(validator.isValidArrayLine(null));
    }

    @Test
    void isValidArrayLine_Eight() {
        System.out.println(validator.isValidArrayLine("   "));
    }

    @Test
    void isValidArrayLine_Nine() {
        System.out.println(validator.isValidArrayLine("1,2,3"));
        System.out.println(validator.isValidArrayLine("1  2"));
    }
}

