package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.HashMap;

// BEGIN
public class App {

    public static void swapKeyValue(KeyValueStorage initialMap) {

        if (initialMap.toMap().isEmpty()) {
            return;
        } else {
            Map <String, String> copyOfInitialMap = new HashMap<>();
            copyOfInitialMap.putAll(initialMap.toMap());
            for (String key : copyOfInitialMap.keySet()) {
                initialMap.unset(key);
            }

            for (String key : copyOfInitialMap.keySet()) {
                String value = copyOfInitialMap.get(key);
                initialMap.set(value, key);
            }
        }

    }
}
// END
