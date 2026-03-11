package Operation;

import java.util.Deque;

public class Duplicate implements Operation {

    public static final String SYMBOL = "dup";

    @Override
    public void applyToStack(final Deque<Integer> stack) {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException(
                    "Duplicating requires that the stack contain at least 1 value");
        }

        final int a = stack.peek();
        stack.push(a);
    }
}
