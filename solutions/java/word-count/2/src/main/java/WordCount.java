import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class WordCount {
    private static final Pattern DELIMITERS =
            Pattern.compile("(?<![a-zA-Z])'|'(?![a-zA-Z])|[^a-zA-Z0-9']+");

    public Map<String, Integer> phrase(final String input) {
        return Arrays.stream(DELIMITERS.split(input))
                .map(String::toLowerCase)
                .filter(Predicate.not(String::isEmpty))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(_ -> 1)));
    }
}
