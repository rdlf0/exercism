import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Proverb {

    private static final String PROVERB_LINE = "For want of a %s the %s was lost.\n";
    private static final String PROVERB_LAST_LINE = "And all for the want of a %s.";

    private String[] words;

    Proverb(String[] words) {
        this.words = words;
    }

    String recite() {
        if (this.words.length == 0) return "";

        return IntStream.range(0, this.words.length - 1)
                .mapToObj(i -> String.format(PROVERB_LINE, this.words[i], this.words[i+1]))
                .collect(Collectors.joining())
                .concat(String.format(PROVERB_LAST_LINE, this.words[0]));
    }

}
