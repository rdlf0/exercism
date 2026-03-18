import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Etl {
    Map<String, Integer> transform(final Map<Integer, List<String>> old) {
        return old.entrySet().stream()
                .flatMap(
                        e ->
                                e.getValue().stream()
                                        .map(letter -> Map.entry(letter.toLowerCase(), e.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
