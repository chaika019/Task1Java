package by.chaika19.task1;

import org.junit.jupiter.api.Test;
import by.chaika19.task1.reader.CustomArrayFileReader;
import by.chaika19.task1.reader.impl.CustomArrayFileReaderImpl;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayFileReaderTest {
    private final CustomArrayFileReader reader = new CustomArrayFileReaderImpl();
    
    @Test
    void readFile_ShouldReadValidFile() throws Exception {
        Path testFile = Path.of("src/test/resources/data/data.txt");
        List<String> result = reader.readFile(testFile.toString());

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("1 2 3 4 5", result.get(0));
    }
}
