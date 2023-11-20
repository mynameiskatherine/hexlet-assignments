package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends exercise.Tag {
    public SingleTag(String tagName, Map<String,String> tagAttributes) {
        super(tagName, tagAttributes);

    }

    @Override
    public String toString() {
        String tagString = "<" + getTagName() + getTagAttributesToString() + ">";
        return tagString;
    }
}
// END
