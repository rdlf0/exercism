package Operation;

import java.util.Deque;

public class Add implements Operation {

    public static final String SYMBOL = "+";

    @Override
    public void applyToStack(final Deque<Integer> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException(
                    "Addition requires that the stack contain at least 2 values");
        }

        final int b = stack.pop();
        final int a = stack.pop();
        stack.push(a + b);
    }
}
