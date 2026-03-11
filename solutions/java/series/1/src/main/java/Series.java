import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Series {

    private static final String ERROR_TOO_BIG = "Slice size is too big.";
    private static final String ERROR_TOO_SMALL = "Slice size is too small.";

    private final String digits;

    Series(final String digits) {
        this.digits = digits;
    }

    List<String> slices(final int size) {
        if (size > digits.length()) {
            throw new IllegalArgumentException(ERROR_TOO_BIG);
        }

        if (size < 1) {
            throw new IllegalArgumentException(ERROR_TOO_SMALL);
        }

        return IntStream.rangeClosed(0, digits.length() - size)
                .mapToObj(i -> digits.substring(i, i + size))
                .collect(Collectors.toList());
    }

}
