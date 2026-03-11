class CalculatorConundrum {
    public String calculate(final int operand1, final int operand2, final String operation) {
        final int result =
                switch (operation) {
                    case "+" -> operand1 + operand2;
                    case "*" -> operand1 * operand2;
                    case "/" -> {
                        if (operand2 == 0) {
                            throw new IllegalOperationException(
                                    "Division by zero is not allowed", new ArithmeticException());
                        }
                        yield operand1 / operand2;
                    }
                    case null -> throw new IllegalArgumentException("Operation cannot be null");
                    case "" -> throw new IllegalArgumentException("Operation cannot be empty");
                    default ->
                            throw new IllegalOperationException(
                                    "Operation '%s' does not exist".formatted(operation));
                };

        return "%d %s %d = %d".formatted(operand1, operation, operand2, result);
    }
}
