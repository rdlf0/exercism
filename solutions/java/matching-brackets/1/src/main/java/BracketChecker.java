import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Set;

class BracketChecker {
    private static final Set<Character> BRACKETS = Set.of('(', ')', '[', ']', '{', '}');
    private static final Map<Character, Character> OPENING_BY_CLOSING =
            Map.of(')', '(', ']', '[', '}', '{');

    private final String expression;
    private final Deque<Character> stack = new ArrayDeque<>();

    BracketChecker(final String expression) {
        this.expression = expression;
    }

    boolean areBracketsMatchedAndNestedCorrectly() {
        if (expression.length() == 1) {
            return false;
        }

        for (final char ch : expression.toCharArray()) {
            if (!BRACKETS.contains(ch)) {
                continue;
            }

            if (!OPENING_BY_CLOSING.containsKey(ch)) {
                stack.push(ch);
                continue;
            }

            if (stack.isEmpty() || stack.poll() != OPENING_BY_CLOSING.get(ch)) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
