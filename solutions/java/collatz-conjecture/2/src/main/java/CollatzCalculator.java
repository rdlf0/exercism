import java.util.function.LongUnaryOperator;

class CollatzCalculator {
    private static final LongUnaryOperator EVEN_OPERATOR = l -> l / 2;
    private static final LongUnaryOperator ODD_OPERATOR = l -> l * 3 + 1;

    int computeStepCount(final int start) {
        if (start < 1) {
            throw new IllegalArgumentException("Only positive integers are allowed");
        }

        long current = start;
        int steps = 0;
        while (current != 1) {
            current =
                    current % 2 == 0
                            ? EVEN_OPERATOR.applyAsLong(current)
                            : ODD_OPERATOR.applyAsLong(current);
            steps++;
        }
        return steps;
    }
}
