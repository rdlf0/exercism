import java.util.Arrays;
import java.util.stream.IntStream;

class SumOfMultiples {

    private final int number;
    private final int[] set;

    SumOfMultiples(final int number, final int[] set) {
        this.number = number;
        this.set = set;
    }

    int getSum() {
        return IntStream.range(1, number)
            .filter(n -> Arrays
                .stream(set)
                .anyMatch(s -> s > 0 && n % s == 0))
            .sum();
    }

}
