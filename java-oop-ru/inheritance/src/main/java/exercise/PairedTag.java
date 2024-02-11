package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends exercise.Tag {
    private String tagTextBody;
    private List<exercise.Tag> childTags;

    public PairedTag(String tagName, Map<String, String> tagAttributes, String tagTextBody, List<exercise.Tag> childTags) {
        super(tagName, tagAttributes);
        this.tagTextBody = tagTextBody;
        this.childTags = childTags;
    }

    @Override
    public String toString() {
        String childTagsString = "";
        if (!childTags.isEmpty()) {
            childTagsString = childTags.stream()
                    .map(m -> m.toString())
                    .collect(Collectors.joining());
        }

        String tagString = new exercise.SingleTag(getTagName(), getTagAttributes()).toString() + tagTextBody + childTagsString + "</" + getTagName() + ">";
        return tagString;
    }
}
// END
