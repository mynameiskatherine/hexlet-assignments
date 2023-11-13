package exercise;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> list, int n) {
        List<String> sortedList = list.stream()
                .sorted(Comparator.comparing(e -> e.getArea()))
                .map(e -> e.toString())
                .collect(Collectors.toList());
        return sortedList.size() > n ? sortedList.subList(0, n) : sortedList;
    }
}
// END
