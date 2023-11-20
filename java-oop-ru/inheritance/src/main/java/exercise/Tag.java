package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    private String tagName;
    private Map<String, String> tagAttributes;

    public Tag(String tagName, Map<String, String> tagAttributes) {
        this.tagName = tagName;
        this.tagAttributes = tagAttributes;
    }

    protected String getTagName() {
        return tagName;
    }

    protected Map<String, String> getTagAttributes() {
        return tagAttributes;
    }

    protected String getTagAttributesToString() {
        StringBuilder tagAttributesString = new StringBuilder();
        if (!tagAttributes.isEmpty()) {
            for (String key : tagAttributes.keySet()) {
                tagAttributesString.append(" ").append(key).append("=\"").append(tagAttributes.get(key)).append("\"");
            }
        }
        return tagAttributesString.toString();
    }
}
// END
