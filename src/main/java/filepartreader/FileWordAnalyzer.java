package filepartreader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileWordAnalyzer {

    private FilePartReader filePartReader;

    public FileWordAnalyzer(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }

    public List<String> getWordsOrderedAlphabetically() throws IOException {
        List<String> words = getAllWords();
        Collections.sort(words);
        return words;
    }

    public List<String> getWordsContainingSubstring (String subString) throws IOException {
        List<String> result = new ArrayList<>();

        getAllWords().stream()
                .filter(word -> word.contains(subString))
                .forEach(result::add);
        return result;
    }


    public List getStringsWhichPalindromes() throws IOException {
        List<String> result = new ArrayList<>();

        getAllWords().stream()
                .filter(this::isPalindrome)
                .forEach(result::add);
        return result;
    }

    private boolean isPalindrome(String word) {
        char[] letters = word.toCharArray();
        int size = letters.length;
        for (int i = 0; i < size; i++) {
            if (letters[i] != letters[size - 1 - i])
                return false;
        }
        return true;
    }


    private List<String> getAllWords() throws IOException {
        return Stream.of(filePartReader.readLines()
                .toLowerCase()
                .replaceAll("[;:.,!?]", "")
                .split("\\s|\n")).distinct().filter(w -> !w.isEmpty()).collect(Collectors.toList());
    }
}
