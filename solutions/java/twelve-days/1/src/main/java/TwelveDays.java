import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class TwelveDays {

    private static final String LINE = "On the %s day of Christmas my true love gave to me: %s.\n";
    private static final List<Pair> VERSES = new ArrayList<>(Arrays.asList(
            null,
            new Pair("first", "a Partridge in a Pear Tree"),
            new Pair("second", "two Turtle Doves"),
            new Pair("third", "three French Hens"),
            new Pair("fourth", "four Calling Birds"),
            new Pair("fifth", "five Gold Rings"),
            new Pair("sixth", "six Geese-a-Laying"),
            new Pair("seventh", "seven Swans-a-Swimming"),
            new Pair("eighth", "eight Maids-a-Milking"),
            new Pair("ninth", "nine Ladies Dancing"),
            new Pair("tenth", "ten Lords-a-Leaping"),
            new Pair("eleventh", "eleven Pipers Piping"),
            new Pair("twelfth", "twelve Drummers Drumming")
    ));

    String verse(int verseNumber) {
        StringBuilder sb = new StringBuilder();
        for (int i = verseNumber; i > 0; i--) {
            sb.append(VERSES.get(i).gift);

            if (i > 2) {
                sb.append(", ");
            } else if (i == 2) {
                sb.append(", and ");
            }
        }

        return String.format(LINE, VERSES.get(verseNumber).order, sb.toString());
    }

    String verses(int startVerse, int endVerse) {
        List<String> result = new ArrayList<>();
        for (int i = startVerse; i <= endVerse; i++) {
            result.add(this.verse(i));
        }

        return String.join("\n", result);
    }

    String sing() {
        return this.verses(1, 12);
    }
}

class Pair {

    String order;
    String gift;

    Pair(String order, String gift) {
        this.order = order;
        this.gift = gift;
    }

}
