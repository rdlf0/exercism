import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Anagram {

    private String word;

    public Anagram(String word) {
        this.word = word;
    }

    public List<String> match(List<String> candidates) {
        int[] wordArray = this.word.toUpperCase().chars().sorted().toArray();

        return candidates.stream()
                .filter(c -> !this.word.toUpperCase().equals(c.toUpperCase()))
                .filter(c -> Arrays.equals(wordArray, c.toUpperCase().chars().sorted().toArray()))
                .collect(Collectors.toList());
    }

}
