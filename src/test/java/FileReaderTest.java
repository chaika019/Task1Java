import by.chaika19.exception.FileReadException;
import by.chaika19.reader.impl.FileReaderImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderImplTest {

    @Test
    void readFile_ShouldReturnLines_WhenFileExists() throws FileReadException {
        FileReaderImpl reader = new FileReaderImpl();
        String path = "src/test/resources/input.txt";

        List<String> lines = reader.readFile(path);

        assertNotNull(lines);
        assertFalse(lines.isEmpty());
    }
}