import by.chaika19.validator.ArrayDataValidator;
import by.chaika19.validator.impl.ArrayDataValidatorImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayDataValidatorImplTest {

    @Test
    void isValid_ShouldReturnTrue_ForCommaSeparatedNumbers() {
        ArrayDataValidator validator = new ArrayDataValidatorImpl();
        String line = "1, 2, 3";

        boolean result = validator.isValid(line);

        System.out.println(result);
    }

    @Test
    void isValid_ShouldReturnTrue_ForSpaceSeparatedNumbers() {
        ArrayDataValidator validator = new ArrayDataValidatorImpl();
        String line = "1 2 3";

        boolean result = validator.isValid(line);

        System.out.println(result);
    }

    @Test
    void isValid_ShouldReturnFalse_ForLetters() {
        ArrayDataValidator validator = new ArrayDataValidatorImpl();
        String line = "asda";

        boolean result = validator.isValid(line);

        System.out.println(result);
    }

    @Test
    void isValid_ShouldReturnFalse_ForMixedLettersAndNumbers() {
        ArrayDataValidator validator = new ArrayDataValidatorImpl();
        String line = "t21f2 21";

        boolean result = validator.isValid(line);

        System.out.println(result);
    }

    @Test
    void isValid_ShouldReturnFalse_ForEmptyString() {
        ArrayDataValidator validator = new ArrayDataValidatorImpl();
        String line = "";

        boolean result = validator.isValid(line);

        System.out.println(result);
    }
}