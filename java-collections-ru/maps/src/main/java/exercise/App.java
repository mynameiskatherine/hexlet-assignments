package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        String[] splitedSentence = sentence.split(" ");
        Map<String, Integer> result = new HashMap<>();
        if (sentence.length() == 0 || splitedSentence.length == 0) {
            return result;
        }

        for (String wordToCount: splitedSentence) {
            int i = 0;
            for (String words: splitedSentence) {
                if (words.equals(wordToCount)) {
                    i = i + 1;
                }
            }
            result.put(wordToCount, i);
        }
        return result;
    }

    public static String toString(Map<String, Integer> map) {
        if (map.size() == 0) {
            return "{}";
        }

        String result = "{" + "\n";
        for (String str: map.keySet()) {
            result = result + "  " + str + ": " + map.get(str) + "\n";
        }
        result = result + "}";
        return result;
    }
}
//END
