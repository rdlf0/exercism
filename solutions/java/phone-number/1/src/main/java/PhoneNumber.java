class PhoneNumber {

    private static final String EXCEPTION_FOUND_LETTERS = "letters not permitted";
    private static final String EXCEPTION_FOUND_PUNCTUATIONS = "punctuations not permitted";
    private static final String EXCEPTION_LENGTH_LT_10 = "incorrect number of digits";
    private static final String EXCEPTION_LENGTH_GT_11 = "more than 11 digits";
    private static final String EXCEPTION_LENGTH_11_START_WITH_1 = "11 digits must start with 1";
    private static final String EXCEPTION_AREA_CODE_START_WITH_0 = "area code cannot start with zero";
    private static final String EXCEPTION_AREA_CODE_START_WITH_1 = "area code cannot start with one";
    private static final String EXCEPTION_EXCHANGE_CODE_START_WITH_0 = "exchange code cannot start with zero";
    private static final String EXCEPTION_EXCHANGE_CODE_START_WITH_1 = "exchange code cannot start with one";

    private String inputNumber;

    PhoneNumber(String inputNumber) {
        this.inputNumber = this.sanitizeInput(inputNumber);

        this.validateInputCharacters();
        this.validateInputLength();

        this.removeCountryCode();

        this.validateAreaCode();
        this.validateExchangeCode();
    }

    private String sanitizeInput(String inputNumber) {
        return inputNumber.replaceAll("[\\s\\-\\(\\)\\.\\+]", "");
    }

    private void validateInputCharacters() {
        if (this.inputNumber.matches("^.*[a-zA-Z]+.*$")) {
            throw new IllegalArgumentException(EXCEPTION_FOUND_LETTERS);
        }

        if (this.inputNumber.matches("^.*\\D+.*$")) {
            throw new IllegalArgumentException(EXCEPTION_FOUND_PUNCTUATIONS);
        }
    }

    private void validateInputLength() {
        if (this.inputNumber.length() < 10) {
            throw new IllegalArgumentException(EXCEPTION_LENGTH_LT_10);
        }

        if (this.inputNumber.length() > 11) {
            throw new IllegalArgumentException(EXCEPTION_LENGTH_GT_11);
        }

        if (this.inputNumber.length() == 11 && !this.inputNumber.startsWith("1")) {
            throw new IllegalArgumentException(EXCEPTION_LENGTH_11_START_WITH_1);
        }
    }

    private void validateAreaCode() {
        if (this.inputNumber.startsWith("0")) {
            throw new IllegalArgumentException(EXCEPTION_AREA_CODE_START_WITH_0);
        }

        if (this.inputNumber.startsWith("1")) {
            throw new IllegalArgumentException(EXCEPTION_AREA_CODE_START_WITH_1);
        }
    }

    private void validateExchangeCode() {
        if (this.inputNumber.charAt(3) == '0') {
            throw new IllegalArgumentException(EXCEPTION_EXCHANGE_CODE_START_WITH_0);
        }

        if (this.inputNumber.charAt(3) == '1') {
            throw new IllegalArgumentException(EXCEPTION_EXCHANGE_CODE_START_WITH_1);
        }
    }

    private void removeCountryCode() {
        if (this.inputNumber.length() == 11 && this.inputNumber.startsWith("1")) {
            this.inputNumber = this.inputNumber.substring(1);
        }
    }

    public String getNumber() {
        return inputNumber;
    }

}
