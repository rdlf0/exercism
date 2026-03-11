package Operation;

import java.util.Deque;

public class Divide implements Operation {

    public static final String SYMBOL = "/";

    @Override
    public void applyToStack(final Deque<Integer> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException(
                    "Division requires that the stack contain at least 2 values");
        }

        final int b = stack.pop();
        if (b == 0) {
            throw new IllegalArgumentException("Division by 0 is not allowed");
        }

        final int a = stack.pop();
        stack.push(a / b);
    }
}
