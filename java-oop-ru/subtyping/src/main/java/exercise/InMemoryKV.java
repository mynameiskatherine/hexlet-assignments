package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {

    private Map<String, String> keyValueInMemoryBD = new HashMap<>();

    public InMemoryKV(Map<String, String> map) {
        keyValueInMemoryBD.putAll(map);
    }

    public void set(String key, String value) {
        keyValueInMemoryBD.put(key, value);
    }

    public void unset(String key) {
        keyValueInMemoryBD.remove(key);
    }

    public String get(String key, String defaultValue) {
        return keyValueInMemoryBD.getOrDefault(key, defaultValue);
    }

    public Map<String, String> toMap() {
        Map<String, String> keyValueInMemoryBDcopy = new HashMap<>();
        keyValueInMemoryBDcopy.putAll(keyValueInMemoryBD);
        return keyValueInMemoryBDcopy;
    }
}
// END
