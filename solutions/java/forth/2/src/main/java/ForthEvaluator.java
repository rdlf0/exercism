import Operation.Add;
import Operation.Divide;
import Operation.Drop;
import Operation.Duplicate;
import Operation.Multiply;
import Operation.Operation;
import Operation.Over;
import Operation.Subtract;
import Operation.Swap;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

class ForthEvaluator {
    private static final Pattern PATTERN_NUMBER = Pattern.compile("\\d+");
    private static final Map<String, Operation> OPERATIONS =
            Map.of(
                    Add.SYMBOL,
                    new Add(),
                    Subtract.SYMBOL,
                    new Subtract(),
                    Multiply.SYMBOL,
                    new Multiply(),
                    Divide.SYMBOL,
                    new Divide(),
                    Duplicate.SYMBOL,
                    new Duplicate(),
                    Drop.SYMBOL,
                    new Drop(),
                    Swap.SYMBOL,
                    new Swap(),
                    Over.SYMBOL,
                    new Over());

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
            } else if (OPERATIONS.containsKey(entry)) {
                final Operation operation = OPERATIONS.get(entry);
                operation.applyToStack(stack);
            } else {
                throw new IllegalArgumentException(
                        "No definition available for operator \"%s\"".formatted(entry));
            }
        }

        return stack.stream().toList().reversed();
    }
}
