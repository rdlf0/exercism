package Operation;

import java.util.Deque;

public class Over implements Operation {

    public static final String SYMBOL = "over";

    @Override
    public void applyToStack(final Deque<Integer> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException(
                    "Overing requires that the stack contain at least 2 values");
        }

        final int b = stack.pop();
        final int a = stack.getFirst();
        stack.push(b);
        stack.push(a);
    }
}
