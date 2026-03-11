import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Operation {
    ADD("Addition", "+", Operation::add, null),
    SUBTRACT("Subtraction", "-", Operation::subtract, null),
    MULTIPLY("Multiplication", "*", Operation::multiply, null),
    DIVIDE("Division", "/", Operation::divide, null),
    DUPLICATE("Duplicating", "dup", null, Operation::duplicate),
    DROP("Dropping", "drop", null, Operation::drop),
    SWAP("Swapping", "swap", Operation::swap, null),
    OVER("Overing", "over", Operation::over, null),
    ;

    private static final Map<String, Operation> BY_SYMBOL =
            Arrays.stream(values())
                    .collect(
                            Collectors.collectingAndThen(
                                    Collectors.toMap(Operation::getSymbol, Function.identity()),
                                    Collections::unmodifiableMap));

    private final String name;
    private final String symbol;
    private final BiFunction<Integer, Integer, List<Integer>> biOperator;
    private final Function<Integer, List<Integer>> monoOperator;

    Operation(
            final String name,
            final String symbol,
            final BiFunction<Integer, Integer, List<Integer>> biOperator,
            final Function<Integer, List<Integer>> monoOperator) {
        if (biOperator == null ^ monoOperator != null) {
            throw new IllegalArgumentException("Only one of the operators must be defined");
        }

        this.name = name;
        this.symbol = symbol;
        this.biOperator = biOperator;
        this.monoOperator = monoOperator;
    }

    public static Operation getBySymbol(final String symbol) {
        return Optional.ofNullable(BY_SYMBOL.get(symbol))
                .orElseThrow(
                        () ->
                                new IllegalArgumentException(
                                        "Undefined operation for symbol %s".formatted(symbol)));
    }

    public static boolean isDefined(final String symbol) {
        return BY_SYMBOL.containsKey(symbol);
    }

    public static List<Integer> add(final int a, final int b) {
        return Collections.singletonList(a + b);
    }

    public static List<Integer> subtract(final int a, final int b) {
        return Collections.singletonList(a - b);
    }

    public static List<Integer> multiply(final int a, final int b) {
        return Collections.singletonList(a * b);
    }

    public static List<Integer> divide(final int a, final int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by 0 is not allowed");
        }
        return Collections.singletonList(a / b);
    }

    public static List<Integer> duplicate(final int a) {
        return List.of(a, a);
    }

    public static List<Integer> drop(final int a) {
        return Collections.emptyList();
    }

    public static List<Integer> swap(final int a, final int b) {
        return List.of(b, a);
    }

    public static List<Integer> over(final int a, final int b) {
        return List.of(a, b, a);
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public BiFunction<Integer, Integer, List<Integer>> getBiOperator() {
        return biOperator;
    }

    public Function<Integer, List<Integer>> getMonoOperator() {
        return monoOperator;
    }
}
