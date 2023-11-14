package exercise;

import java.util.Map;
import java.util.HashMap;
// BEGIN
public class FileKV implements KeyValueStorage {

    private String filePath;

    public FileKV(String filePath, Map<String, String> keyValueFileBD) {
        this.filePath = new String(filePath);
        Utils.writeFile(this.filePath, Utils.serialize(keyValueFileBD));
    }

    public void set(String key, String value) {
        Map<String, String> tempKeyValueFileBD = new HashMap<>();
        tempKeyValueFileBD.putAll(Utils.unserialize(Utils.readFile(filePath)));
        tempKeyValueFileBD.put(key, value);
        Utils.writeFile(filePath, Utils.serialize(tempKeyValueFileBD));
    }
    public void unset(String key) {
        Map<String, String> tempKeyValueFileBD = new HashMap<>();
        tempKeyValueFileBD.putAll(Utils.unserialize(Utils.readFile(filePath)));
        tempKeyValueFileBD.remove(key);
        Utils.writeFile(filePath, Utils.serialize(tempKeyValueFileBD));
    }
    public String get(String key, String defaultValue) {
        return (Utils.unserialize(Utils.readFile(filePath))).getOrDefault(key, defaultValue);
    }
    public Map<String, String> toMap() {
        return Utils.unserialize(Utils.readFile(filePath));
    }
}
// END
