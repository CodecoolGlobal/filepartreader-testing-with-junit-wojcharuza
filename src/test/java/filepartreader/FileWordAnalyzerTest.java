package filepartreader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FileWordAnalyzerTest {

    private FilePartReader fileReaderMock;
    private FileWordAnalyzer wordAnalyzer;

    @BeforeEach
    void setup() {
        fileReaderMock = Mockito.mock(FilePartReader.class);
        wordAnalyzer = new FileWordAnalyzer(fileReaderMock);
    }

    @Test
    void testIfOrderedWordsMethodCallsReadlinesMethod() throws IOException {
        when(fileReaderMock.readLines()).thenReturn("halo");
        wordAnalyzer.getWordsOrderedAlphabetically();
        verify(fileReaderMock).readLines();
    }

    @Test
    void testIfWordsAreOrderedCorrectly() throws IOException {
        List<String> expected = Arrays.asList("aa", "bb", "cc", "dd");
        when(fileReaderMock.readLines()).thenReturn("aa bb cc dd");
        assertEquals(expected, wordAnalyzer.getWordsOrderedAlphabetically());
    }

    @Test
    void testIfWordContainingSubstringWorksCorrectly() throws IOException{
        String substring = "halo";
        List<String> expected = Arrays.asList("mahalo");
        when(fileReaderMock.readLines()).thenReturn("hola mahalo hlo a");
        assertEquals(expected, wordAnalyzer.getWordsContainingSubstring(substring));

    }

    @Test
    void testIfPalindromeIsFoundCorrectly() throws IOException{
        List<String> expected = Arrays.asList("kajak");
        when(fileReaderMock.readLines()).thenReturn("adc halo kajak wop ppw");
        assertEquals(expected, wordAnalyzer.getStringsWhichPalindromes());
    }

}