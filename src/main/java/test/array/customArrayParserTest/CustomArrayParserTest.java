package test.array.customArrayParserTest;

import org.junit.jupiter.api.Test;
import by.chaika19.task1.exception.CustomArrayException;
import by.chaika19.task1.parser.CustomArrayDataParser;
import by.chaika19.task1.parser.impl.CustomArrayDataParserImpl;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayParserTest {
    private final CustomArrayDataParser customArrayDataParser = new CustomArrayDataParserImpl();

    @Test
    public void parser_validArray() throws CustomArrayException {
        int[] expected = new int[]{1, 2, 3, 4, 5};
        int[] testArray = customArrayDataParser.parseLine("1 2 3 4 5");
        assertArrayEquals(expected, testArray);
    }

    @Test
    public void parser_invalidArray_one() throws CustomArrayException {
        assertThrows(CustomArrayException.class, () -> customArrayDataParser.parseLine("1 - 2"));
    }

    @Test
    public void parser_invalidArray_two() throws CustomArrayException {
        assertThrows(CustomArrayException.class, () -> customArrayDataParser.parseLine("1; 2"));
    }

    @Test
    public void parser_emptyArray() throws CustomArrayException {
        assertThrows(CustomArrayException.class, () -> customArrayDataParser.parseLine(" "));
    }

    @Test
    public void parser_nullArray() throws CustomArrayException {
        assertThrows(CustomArrayException.class, () -> customArrayDataParser.parseLine(null));
    }
}
