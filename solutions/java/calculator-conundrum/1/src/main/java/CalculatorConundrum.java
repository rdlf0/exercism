class CalculatorConundrum {
    public String calculate(final int operand1, final int operand2, final String operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Operation cannot be null");
        }

        if (operation.isEmpty()) {
            throw new IllegalArgumentException("Operation cannot be empty");
        }

        if (operation.equals("/") && operand2 == 0) {
            throw new IllegalOperationException(
                    "Division by zero is not allowed", new ArithmeticException());
        }

        final int result =
                switch (operation) {
                    case "+" -> operand1 + operand2;
                    case "*" -> operand1 * operand2;
                    case "/" -> operand1 / operand2;
                    default ->
                            throw new IllegalOperationException(
                                    "Operation '%s' does not exist".formatted(operation));
                };

        return "%d %s %d = %d".formatted(operand1, operation, operand2, result);
    }
}
