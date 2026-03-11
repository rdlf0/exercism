import java.util.function.IntUnaryOperator;

class CollatzCalculator {
    private static final IntUnaryOperator EVEN_OPERATOR = i -> i / 2;
    private static final IntUnaryOperator ODD_OPERATOR = i -> i * 3 + 1;

    int computeStepCount(int start) {
        if (start < 1) {
            throw new IllegalArgumentException("Only positive integers are allowed");
        }
        int steps = 0;
        while (start != 1) {
            start =
                    start % 2 == 0
                            ? EVEN_OPERATOR.applyAsInt(start)
                            : ODD_OPERATOR.applyAsInt(start);
            steps++;
        }
        return steps;
    }
}
