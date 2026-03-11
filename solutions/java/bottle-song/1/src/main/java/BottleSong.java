import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BottleSong {

    private static final List<String> NUMBER_NAMES =
            List.of(
                    "no", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
                    "ten");
    private static final String VERSE =
            """
%1$s%2$s green %3$s hanging on the wall,
%1$s%2$s green %3$s hanging on the wall,
And if one green bottle should accidentally fall,
There'll be %4$s green %5$s hanging on the wall.
""";

    String recite(final int startBottles, final int takeDown) {
        return IntStream.range(0, takeDown)
                .map(i -> startBottles - i)
                .limit(takeDown)
                .boxed()
                .map(
                        n -> {
                            final String currentNumber = NUMBER_NAMES.get(n);
                            final String nextNumber = NUMBER_NAMES.get(n - 1);
                            return VERSE.formatted(
                                    currentNumber.substring(0, 1).toUpperCase(),
                                    currentNumber.substring(1),
                                    n == 1 ? "bottle" : "bottles",
                                    nextNumber,
                                    n == 2 ? "bottle" : "bottles");
                        })
                .collect(Collectors.joining("\n"));
    }
}
