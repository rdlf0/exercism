import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class House {

    private static final String FIRST_LINE = "This is %s";
    private static final List<String> VERSES = new ArrayList<>(Arrays.asList(
            "the house that Jack built.",
            "the malt that lay in",
            "the rat that ate",
            "the cat that killed",
            "the dog that worried",
            "the cow with the crumpled horn that tossed",
            "the maiden all forlorn that milked",
            "the man all tattered and torn that kissed",
            "the priest all shaven and shorn that married",
            "the rooster that crowed in the morn that woke",
            "the farmer sowing his corn that kept",
            "the horse and the hound and the horn that belonged to"
    ));

    public String verse(int line) {
        String assembled = IntStream.range(0, line)
                .map(i -> line - i - 1) // reverse
                .mapToObj(VERSES::get)
                .collect(Collectors.joining(" "));

        return String.format(FIRST_LINE, assembled);
    }

    public String verses(int from, int to) {
        return IntStream.rangeClosed(from, to)
                .mapToObj(this::verse)
                .collect(Collectors.joining("\n"));
    }

    public String sing() {
        return this.verses(1, VERSES.size());
    }

}
