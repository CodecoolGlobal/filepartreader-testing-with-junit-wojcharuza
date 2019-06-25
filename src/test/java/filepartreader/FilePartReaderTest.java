package filepartreader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilePartReaderTest {

    private static FilePartReader filePartReader;
    private static String filePath;

    @BeforeAll
    static void setup() {
        filePartReader = new FilePartReader();
        filePath = "src/main/resources/testFile";
    }

    @Test
    void testToLineIsSmallerThanFromLine(){
        Integer toLine = 1;
        Integer fromLine = 2;

        assertThrows(IllegalArgumentException.class, () -> filePartReader.setup(filePath, fromLine, toLine));
    }

    @Test
    void testReadFileContent() throws IOException {
        String expectedFileContent = "Lorem ipsum dolor sit amet,\n" +
                        "consectetur adipiscing elit,\n" +
                        "sed do eiusmod tempor incididunt ut\n" +
                        "labore et dolore magna aliqua.";

        filePartReader.setup(filePath, 1, 100);
        assertEquals(expectedFileContent, filePartReader.read());
    }

    @Test
    void testFileIsNotFound(){
        String wrongPath = "src/wrongFile";
        filePartReader.setup(wrongPath, 1, 10);
        assertThrows(IOException.class, () -> filePartReader.read());
    }

    @Test
    void testWhenLinesToReadAreNotInFile() throws IOException {
        String expected = "";
        filePartReader.setup(filePath, 100, 200);
        assertEquals(expected, filePartReader.readLines());
    }

}