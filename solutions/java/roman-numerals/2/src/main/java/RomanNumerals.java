class RomanNumeral {

    private final int number;

    private static final String[][] CODES = {
            {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
            {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
            {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
            {"", "M", "MM", "MMM"},
    };

    RomanNumeral(final int number) {
        this.number = number;
    }

    public String getRomanNumeral() {
        return CODES[3][number / 1000] +
            CODES[2][number % 1000 / 100] +
            CODES[1][number % 100 / 10] +
            CODES[0][number % 10];
    }
}
