package filepartreader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/resources/testFile";
        Integer fromLine = 1;
        Integer toLine = 5;


        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup(filePath, fromLine, toLine);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);

        try {
            System.out.println(fileWordAnalyzer.getWordsOrderedAlphabetically());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(fileWordAnalyzer.getWordsContainingSubstring("em"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(fileWordAnalyzer.getStringsWhichPalindromes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
