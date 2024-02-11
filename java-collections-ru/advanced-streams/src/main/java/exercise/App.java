package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;


// BEGIN
public class App {
    public static String getForwardedVariables(String config) {
        String result = config.lines()
                .filter(x -> x.startsWith("environment="))
                .map(x -> x.substring(13, (x.length() - 1)))
                .flatMap(x -> Arrays.stream(x.split(",")))
                .map(x -> x.trim())
                .filter(x -> x.startsWith("X_FORWARDED"))
                .map(x -> x.substring(12))
                .collect(Collectors.joining(","));

        return result;
    }
}
//END
