import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BottleSong {

    private static final List<String> NUMBER_NAMES =
            List.of(
                    "No", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
                    "Ten");
    private static final String VERSE =
            """
            %1$s green %2$s hanging on the wall,
            %1$s green %2$s hanging on the wall,
            And if one green bottle should accidentally fall,
            There'll be %3$s green %4$s hanging on the wall.
            """;

    String recite(final int startBottles, final int takeDown) {
        return IntStream.range(0, takeDown)
                .map(i -> startBottles - i)
                .limit(takeDown)
                .boxed()
                .map(
                        n ->
                                VERSE.formatted(
                                        NUMBER_NAMES.get(n),
                                        n == 1 ? "bottle" : "bottles",
                                        NUMBER_NAMES.get(n - 1).toLowerCase(),
                                        n == 2 ? "bottle" : "bottles"))
                .collect(Collectors.joining("\n"));
    }
}
