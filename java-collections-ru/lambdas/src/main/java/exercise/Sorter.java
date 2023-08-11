package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
public class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        List<String> result = users.stream()
                .filter( user -> user.get("gender").equals("male") )
                .sorted( (user1, user2) -> ( LocalDate.parse(user1.get("birthday")).compareTo(LocalDate.parse(user2.get("birthday")))) )
                .map(user -> user.get("name"))
                .collect( Collectors.toList() );
        return result;
    }
}
// E
