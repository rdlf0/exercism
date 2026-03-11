package Operation;

import java.util.Deque;

public class Drop implements Operation {

    public static final String SYMBOL = "drop";

    @Override
    public void applyToStack(final Deque<Integer> stack) {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException(
                    "Dropping requires that the stack contain at least 1 value");
        }

        stack.pop();
    }
}
