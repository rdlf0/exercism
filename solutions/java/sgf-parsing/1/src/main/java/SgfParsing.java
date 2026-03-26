import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SgfParsing {
    private static int parseProperties(final String input, int i, final SgfNode node)
            throws SgfParsingException {
        final Map<String, List<String>> properties = new HashMap<>();
        while (i < input.length()) {
            final char ch = input.charAt(i);
            if (Character.isWhitespace(ch)) {
                i++;
            } else if (ch == ';' || ch == '(' || ch == ')') {
                break;
            } else if (Character.isLowerCase(ch)) {
                throw new SgfParsingException("property must be in uppercase");
            } else if (Character.isUpperCase(ch)) {
                final StringBuilder keyBuilder = new StringBuilder();
                while (i < input.length() && Character.isUpperCase(input.charAt(i))) {
                    keyBuilder.append(input.charAt(i++));
                }
                final String key = keyBuilder.toString();

                final List<String> values = new ArrayList<>();
                while (i < input.length() && input.charAt(i) == '[') {
                    i++;
                    final StringBuilder valueBuilder = new StringBuilder();
                    i = parseValue(input, i, valueBuilder);
                    values.add(valueBuilder.toString());
                    i++;
                }
                if (values.isEmpty()) {
                    throw new SgfParsingException("properties without delimiter");
                }
                properties.put(key, values);
            } else {
                i++;
            }
        }
        node.setProperties(properties);

        return i;
    }

    private static int parseValue(final String input, int i, final StringBuilder valueBuilder) {
        while (i < input.length() && input.charAt(i) != ']') {
            final char ch = input.charAt(i);
            switch (ch) {
                case '\\' -> {
                    i++;
                    if (i >= input.length()) {
                        continue;
                    }
                    final char escaped = input.charAt(i);
                    switch (escaped) {
                        case '\n' -> {}
                        case '\t' -> valueBuilder.append(' ');
                        default -> valueBuilder.append(escaped);
                    }
                }
                case '\t' -> valueBuilder.append(' ');
                default -> valueBuilder.append(ch);
            }
            i++;
        }
        return i;
    }

    public SgfNode parse(final String input) throws SgfParsingException {
        if (input == null || input.isEmpty()) {
            throw new SgfParsingException("tree missing");
        }

        final Deque<SgfNode> stack = new LinkedList<>();
        SgfNode root = null;
        SgfNode current = null;
        int i = 0;
        while (i < input.length()) {
            final char ch = input.charAt(i);
            switch (ch) {
                case '(' -> {
                    stack.push(current);
                    i++;
                }
                case ')' -> {
                    if (!stack.isEmpty()) {
                        current = stack.pop();
                    }
                    if (root == null) {
                        throw new SgfParsingException("tree with no nodes");
                    }
                    i++;
                }
                case ';' -> {
                    if (stack.isEmpty()) {
                        throw new SgfParsingException("tree missing");
                    }
                    final SgfNode newNode = new SgfNode();
                    if (root == null) {
                        root = newNode;
                    } else if (current != null) {
                        current.appendChild(newNode);
                    }
                    current = newNode;
                    i++;
                    i = parseProperties(input, i, current);
                }
                default -> i++;
            }
        }

        return root;
    }
}
