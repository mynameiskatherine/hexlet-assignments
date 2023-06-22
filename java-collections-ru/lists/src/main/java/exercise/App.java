package exercise;

import java.util.Arrays;
import java.util.ArrayList;

// BEGIN
public class App {
    public static boolean scrabble(String symbols, String word) {
        if (symbols.equals("")) {
            if (symbols.equals(word)) {
                return true;
            } else {
                return false;
            }
        }
        if (word.equals("")) {
            return true;
        }

        symbols = symbols.toLowerCase();
        word = word.toLowerCase();
        String[] symbolsArray = symbols.split("");
        ArrayList<String> symbolsList = new ArrayList<>(Arrays.asList(symbolsArray));
        String[] wordArray = word.split("");
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(wordArray));
        for (var wordChar: wordList) {
            if (symbolsList.contains(wordChar)) {
                symbolsList.remove(wordChar);
            } else {
                return false;
            }
        }
        return true;
    }
}
//END
