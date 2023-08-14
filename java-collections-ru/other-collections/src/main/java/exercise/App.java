package exercise;

import java.util.*;

// BEGIN
public class App {
    public static LinkedHashMap<String, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {

        Map<String, String> sortedMap = new TreeMap<>();

        for (String keyFromData1: data1.keySet()) {
            if (data2.containsKey(keyFromData1)) {
                if ((data1.get(keyFromData1)).equals(data2.get(keyFromData1))) {
                    sortedMap.put(keyFromData1, "unchanged");
                } else {
                    sortedMap.put(keyFromData1, "changed");
                }
            }
        }

        Set<String> added = new TreeSet<>(data2.keySet());
        added.removeAll(data1.keySet());
        for (String s: added) {
            sortedMap.put(s, "added");
        }

        Set<String> deleted = new TreeSet<>(data1.keySet());
        deleted.removeAll(data2.keySet());
        for (String s: deleted) {
            sortedMap.put(s, "deleted");
        }

        return new LinkedHashMap<>(sortedMap);
/*
        Set<Map.Entry<String,String>> result = data1.keySet().stream()
                .filter(s -> data2.containsKey(s))
                .filter(s -> (data1.get(s)).equals(data2.get(s)))
                .map(s -> new AbstractMap.SimpleEntry<String,String>(s, "unchanged"))
                .collect(Collectors.toSet());
*/

    }
}
//END
