package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
import java.util.Set;

public  class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> bookList, Map<String, String> whatToFind) {
        List<Map<String, String>> result = new ArrayList<>();

        for (Map<String, String> book: bookList) {
            Set<Map.Entry<String, String>> bookAsSet = book.entrySet();
            Set<Map.Entry<String, String>> whatToFindAsSet = whatToFind.entrySet();
            if (bookAsSet.containsAll(whatToFindAsSet)) {
                result.add(book);
            }
        }

        return result;
    }
}
//END
