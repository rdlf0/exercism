import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class TwelveDays {

    private static final String LINE = "On the %s day of Christmas my true love gave to me: %s.\n";
    private static final List<Verse> VERSES = new ArrayList<>(Arrays.asList(
            null,
            new Verse("first", "a Partridge in a Pear Tree"),
            new Verse("second", "two Turtle Doves, and "),
            new Verse("third", "three French Hens, "),
            new Verse("fourth", "four Calling Birds, "),
            new Verse("fifth", "five Gold Rings, "),
            new Verse("sixth", "six Geese-a-Laying, "),
            new Verse("seventh", "seven Swans-a-Swimming, "),
            new Verse("eighth", "eight Maids-a-Milking, "),
            new Verse("ninth", "nine Ladies Dancing, "),
            new Verse("tenth", "ten Lords-a-Leaping, "),
            new Verse("eleventh", "eleven Pipers Piping, "),
            new Verse("twelfth", "twelve Drummers Drumming, ")
    ));

    String verse(int verseNumber) {
        String gifts = IntStream.range(0, verseNumber)
                .mapToObj(i -> VERSES.get(verseNumber - i).getGift())
                .collect(Collectors.joining());

        return String.format(LINE, VERSES.get(verseNumber).getOrdinal(), gifts);
    }

    String verses(int startVerse, int endVerse) {
        return IntStream.range(startVerse, endVerse + 1)
                .mapToObj(this::verse)
                .collect(Collectors.joining("\n"));
    }

    String sing() {
        return this.verses(1, 12);
    }
}
