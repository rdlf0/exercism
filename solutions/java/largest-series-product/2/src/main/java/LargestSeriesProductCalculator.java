import java.util.stream.IntStream;

class LargestSeriesProductCalculator {

    private static final String EXCEPTION_NON_DIGITS = "String to search may only contain digits.";
    private static final String EXCEPTION_SERIES_LEN_LEQ = "Series length must be less than or equal to the length of the string to search.";
    private static final String EXCEPTION_SERIES_LEN_NEG = "Series length must be non-negative.";

    private String inputNumber;

    LargestSeriesProductCalculator(String inputNumber) {
        this.inputNumber = inputNumber;
        this.validateInputNumber();
    }

    long calculateLargestProductForSeriesLength(int numberOfDigits) {
        this.validateSeriesLength(numberOfDigits);

        return IntStream.rangeClosed(0, this.inputNumber.length() - numberOfDigits)
                .map(i -> this.calculateSeriesProduct(i, numberOfDigits))
                .max()
                .orElse(0);
    }

    private int calculateSeriesProduct(int from, int len) {
        return this.inputNumber
                .substring(from, from + len)
                .chars()
                .map(Character::getNumericValue)
                .reduce(1, (a, b) -> a * b);
    }

    private void validateInputNumber() {
        if (this.inputNumber.matches(".*\\D+.*")) {
            throw new IllegalArgumentException(EXCEPTION_NON_DIGITS);
        }
    }

    private void validateSeriesLength(int numberOfDigits) {
        if (numberOfDigits > this.inputNumber.length()) {
            throw new IllegalArgumentException(EXCEPTION_SERIES_LEN_LEQ);
        }

        if (numberOfDigits < 0) {
            throw new IllegalArgumentException(EXCEPTION_SERIES_LEN_NEG);
        }
    }
}
