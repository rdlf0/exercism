import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class ParallelLetterFrequency {
    private final String[] texts;

    ParallelLetterFrequency(final String[] texts) {
        this.texts = texts;
    }

    Map<Character, Integer> countLetters() {
        return Arrays.stream(texts)
                .flatMapToInt(String::chars)
                .mapToObj(ch -> (char) ch)
                .filter(Character::isLetter)
                .map(Character::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(_ -> 1)));
    }
}
