package exercise;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

// BEGIN
@Getter
@AllArgsConstructor
@NoArgsConstructor
// END
public class User {
    @JsonProperty
    int id;
    @JsonProperty
    String firstName;
    @JsonProperty
    String lastName;
    @JsonProperty
    int age;
}
