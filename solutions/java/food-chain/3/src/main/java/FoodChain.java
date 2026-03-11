import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class FoodChain {

    private static final String FIRST_LINE = "I know an old lady who swallowed a %s.";
    private static final String LAST_LINE_VERSE = "I don't know why she swallowed the fly. Perhaps she'll die.";
    private static final String LAST_LINE_SONG = "She's dead, of course!";
    private static final String MIDDLE_LINE = "She swallowed the %s to catch the %s%s.";
    private static final List<String> ANIMALS = new ArrayList<>(Arrays.asList(
            null,
            "fly",
            "spider",
            "bird",
            "cat",
            "dog",
            "goat",
            "cow",
            "horse"
    ));
    private static final List<String> SECOND_LINES = new ArrayList<>(Arrays.asList(
            null,
            null,
            "It wriggled and jiggled and tickled inside her.",
            "How absurd to swallow a bird!",
            "Imagine that, to swallow a cat!",
            "What a hog, to swallow a dog!",
            "Just opened her throat and swallowed a goat!",
            "I don't know how she swallowed a cow!"
    ));

    public String verse(int verseNumber) {
        return this.getFirstLine(verseNumber)
                .concat(this.getSecondLine(verseNumber))
                .concat(this.getMiddleLines(verseNumber))
                .concat(this.getLastLine(verseNumber));
    }

    private String getFirstLine(int verseNumber) {
        return String.format(FIRST_LINE, ANIMALS.get(verseNumber)).concat("\n");
    }

    private String getSecondLine(int verseNumber) {
        if (verseNumber > 1 && verseNumber < 8) {
            return SECOND_LINES.get(verseNumber).concat("\n");
        }

        return "";
    }

    private String getMiddleLines(int verseNumber) {
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = verseNumber; i < 8 && i > 1; i--) {
//            sb.append(
//                    String.format(
//                            MIDDLE_LINE,
//                            ANIMALS.get(i),
//                            ANIMALS.get(i - 1),
//                            this.getAnimalSuffix(i))
//            );
//            sb.append("\n");
//        }
//
//        return sb.toString();

        if (verseNumber == 8) return "";

        return IntStream.range(2, verseNumber)
                .map(i -> verseNumber - i + 1)
                .mapToObj(i -> String.format(
                            MIDDLE_LINE,
                            ANIMALS.get(i),
                            ANIMALS.get(i - 1),
                            this.getAnimalSuffix(i)))
                .collect(Collectors.joining("\n", "", "\n"));
    }

    private String getAnimalSuffix(int verseNumber) {
        if (verseNumber == 3) {
            String spiderSuffix = SECOND_LINES
                    .get(verseNumber - 1)
                    .replaceFirst("It", "that")
                    .replace(".", "");

            return " ".concat(spiderSuffix);
        }

        return "";
    }

    private String getLastLine(int verseNumber) {
        return verseNumber == 8 ? LAST_LINE_SONG : LAST_LINE_VERSE;
    }

    public String verses(int from, int to) {
        return IntStream.rangeClosed(from, to)
                .mapToObj(this::verse)
                .collect(Collectors.joining("\n\n"));
    }

}
