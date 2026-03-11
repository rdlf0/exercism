import java.util.stream.IntStream;

class WordProblemSolver {

    int solve(final String question) {

        final String expression = question
            .replaceAll("What is (.*)\\?", "$1")
            .replaceAll("(\\d+)(st|nd|rd|th) power", "$1")
            .replace("plus", "+")
            .replace("minus", "-")
            .replace("multiplied by", "*")
            .replace("divided by", "/")
            .replace("raised to the", "^");

        if (!expression.matches("-?\\d+( [*+-/^] -?\\d+)*")) {
            throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
        }

        final String[] parts = expression.split(" ");

        return IntStream.iterate(1, i -> i < parts.length, i -> i += 2)
            .reduce(
                Integer.parseInt(parts[0]),
                (a, b) -> operate(
                    parts[b], a, Integer.parseInt(parts[b + 1])));
    }

    private static int operate(final String operation, final int a, final int b) {
        switch (operation) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            case "^":
                return (int) Math.pow(a, b);
            default:
                return 0;
        }
    }

}
