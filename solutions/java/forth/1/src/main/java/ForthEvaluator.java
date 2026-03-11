import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

class ForthEvaluator {
    private static final Pattern PATTERN_NUMBER = Pattern.compile("\\d+");

    private static String applyUserDefinitions(final List<String> input) {
        final List<String> reversedInput = input.reversed();
        final AtomicReference<String> expression = new AtomicReference<>();

        for (final String entry : reversedInput) {
            if (!entry.contains(":") || !entry.contains(";")) {
                expression.set(entry.toLowerCase());
                continue;
            }

            final List<String> userDefinition = List.of(entry.split(" "));
            final String target = userDefinition.get(1).toLowerCase();

            if (PATTERN_NUMBER.matcher(target).find()) {
                throw new IllegalArgumentException("Cannot redefine numbers");
            }

            final String currentExpression = expression.get();
            if (currentExpression == null) {
                continue;
            }

            final List<String> replacements = userDefinition.subList(2, userDefinition.size() - 1);
            final String replacement = String.join(" ", replacements).toLowerCase();

            expression.set(currentExpression.replace(target, replacement));
        }

        return expression.get();
    }

    static void applyOperation(final Deque<Integer> stack, final Operation operation) {
        final AtomicReference<List<Integer>> result = new AtomicReference<>(new ArrayList<>());
        if (operation.getBiOperator() != null) {
            if (stack.size() < 2) {
                throw new IllegalArgumentException(
                        "%s requires that the stack contain at least 2 values"
                                .formatted(operation.getName()));
            }
            final int b = stack.pop();
            final int a = stack.pop();
            result.set(operation.getBiOperator().apply(a, b));
        } else if (operation.getMonoOperator() != null) {
            if (stack.isEmpty()) {
                throw new IllegalArgumentException(
                        "%s requires that the stack contain at least 1 value"
                                .formatted(operation.getName()));
            }

            final int a = stack.pop();
            result.set(operation.getMonoOperator().apply(a));
        }

        result.get().forEach(stack::push);
    }

    List<Integer> evaluateProgram(final List<String> input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Non empty input must be present");
        }

        final String expression = applyUserDefinitions(input);
        if (expression == null) {
            return Collections.emptyList();
        }

        final List<String> sequence = Arrays.stream(expression.split(" ")).toList();
        final Deque<Integer> stack = new ArrayDeque<>();

        for (final String entry : sequence) {
            if (PATTERN_NUMBER.matcher(entry).find()) {
                final int number = Integer.parseInt(entry);
                stack.push(number);
            } else if (Operation.isDefined(entry)) {
                final Operation operation = Operation.getBySymbol(entry);
                applyOperation(stack, operation);
            } else {
                throw new IllegalArgumentException(
                        "No definition available for operator \"%s\"".formatted(entry));
            }
        }

        return stack.stream().toList().reversed();
    }
}
