class LargestSeriesProductCalculator {

    private static final String EXCEPTION_NON_DIGITS = "String to search may only contain digits.";
    private static final String EXCEPTION_SERIES_LEN_LEQ = "Series length must be less than or equal to the length of the string to search.";
    private static final String EXCEPTION_SERIES_LEN_NEG = "Series length must be non-negative.";

    private char[] inputNumber;

    LargestSeriesProductCalculator(String inputNumber) {
        this.validateInputNumber(inputNumber);
        this.inputNumber = inputNumber.toCharArray();
    }

    long calculateLargestProductForSeriesLength(int numberOfDigits) {
        this.validateSeriesLength(numberOfDigits);

        long maxProduct = Integer.MIN_VALUE;

        for (int i = 0; i < this.inputNumber.length - numberOfDigits + 1; i++) {
            long product = 1;
            for (int j = i; j < i + numberOfDigits; j++) {
                product *= Character.getNumericValue(this.inputNumber[j]);
            }

            maxProduct = Math.max(maxProduct, product);
        }

        return maxProduct;
    }

    private void validateInputNumber(String inputNumber) {
        if (inputNumber.matches(".*\\D+.*")) {
            throw new IllegalArgumentException(EXCEPTION_NON_DIGITS);
        }
    }

    private void validateSeriesLength(int numberOfDigits) {
        if (numberOfDigits > this.inputNumber.length) {
            throw new IllegalArgumentException(EXCEPTION_SERIES_LEN_LEQ);
        }

        if (numberOfDigits < 0) {
            throw new IllegalArgumentException(EXCEPTION_SERIES_LEN_NEG);
        }
    }
}
