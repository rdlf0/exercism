public class Say {
    private static final String[] UNITS = {
        "",
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine",
        "ten",
        "eleven",
        "twelve",
        "thirteen",
        "fourteen",
        "fifteen",
        "sixteen",
        "seventeen",
        "eighteen",
        "nineteen"
    };

    private static final String[] TENS = {
        "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    private static String convert(final Long number) {
        return switch (number) {
            case final Long _ when number < 0 -> throw new IllegalArgumentException();
            case final Long n when n < 20 -> UNITS[n.intValue()];
            case final Long n when n < 100 -> {
                final StringBuilder sb = new StringBuilder();
                sb.append(TENS[(int) (n / 10)]);
                if (n % 10 != 0) {
                    sb.append("-");
                    sb.append(convert(n % 10));
                }
                yield sb.toString();
            }
            case final Long n when n < 1_000 -> convert(n, 2, "hundred");
            case final Long n when n < 1_000_000 -> convert(n, 3, "thousand");
            case final Long n when n < 1_000_000_000 -> convert(n, 6, "million");
            case final Long n when n < 1_000_000_000_000L -> convert(n, 9, "billion");
            default -> throw new IllegalArgumentException("");
        };
    }

    public static String convert(final Long n, final int exponent, final String unit) {
        final int divisor = (int) Math.pow(10, exponent);
        final StringBuilder sb = new StringBuilder();
        sb.append(convert(n / divisor));
        sb.append(" ");
        sb.append(unit);
        if (n % divisor != 0) {
            sb.append(" ");
            sb.append(convert(n % divisor));
        }
        return sb.toString();
    }

    public String say(final long number) {
        return number == 0 ? "zero" : convert(number);
    }
}
