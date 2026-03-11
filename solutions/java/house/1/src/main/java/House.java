import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class House {

    private static final String FIRST_LINE = "This is the %s";
    private static final String LINE = "that %s the %s";
    private static final List<Verse> VERSES = new ArrayList<>(Arrays.asList(
            null,
            new Verse("lay in", "house that Jack built."),
            new Verse("ate", "malt "),
            new Verse("killed", "rat "),
            new Verse("worried", "cat "),
            new Verse("tossed", "dog "),
            new Verse("milked", "cow with the crumpled horn "),
            new Verse("kissed", "maiden all forlorn "),
            new Verse("married", "man all tattered and torn "),
            new Verse("woke", "priest all shaven and shorn "),
            new Verse("kept", "rooster that crowed in the morn "),
            new Verse("belonged to", "farmer sowing his corn "),
            new Verse(null, "horse and the hound and the horn ")
    ));

    public String verse(int line) {
        return IntStream.range(0, line)
                .mapToObj(i -> {
                    if (i == 0) {
                        return String.format(
                                FIRST_LINE,
                                VERSES.get(line - i).getSubject()
                        );
                    } else {
                        return String.format(
                                LINE,
                                VERSES.get(line - i).getPredicate(),
                                VERSES.get(line - i).getSubject()
                        );
                    }
                })
                .collect(Collectors.joining());
    }

    public String verses(int from, int to) {
        return IntStream.range(from, to + 1)
                .mapToObj(this::verse)
                .collect(Collectors.joining("\n"));
    }

    public String sing() {
        return this.verses(1, VERSES.size() - 1);
    }

}
