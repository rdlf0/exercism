import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        final int len = String.valueOf(number).length();

        return IntStream.range(0, len)
                .mapToObj(i -> {
                    final int row = len - i - 1;
                    final int col = (number / (int) (Math.pow(10, row))) % 10;
                    return CODES[row][col];
                })
                .collect(Collectors.joining());
    }
}
