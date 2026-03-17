import java.util.ArrayList;
import java.util.List;

class BaseConverter {
    private final int base10Number;

    BaseConverter(final int originalBase, final int[] originalDigits) {
        if (originalBase < 2) {
            throw new IllegalArgumentException("Bases must be at least 2.");
        }
        this.base10Number = toBase10(originalBase, originalDigits);
    }

    private static int toBase10(final int fromBase, final int[] digits) {
        int number = 0;
        for (int i = 0; i < digits.length; i++) {
            final int current = digits[i];
            if (current < 0) {
                throw new IllegalArgumentException("Digits may not be negative.");
            }

            if (current >= fromBase) {
                throw new IllegalArgumentException(
                        "All digits must be strictly less than the base.");
            }
            number += current * Math.powExact(fromBase, digits.length - i - 1);
        }

        return number;
    }

    int[] convertToBase(final int newBase) {
        if (newBase < 2) {
            throw new IllegalArgumentException("Bases must be at least 2.");
        }

        if (base10Number == 0) {
            return new int[] {0};
        }

        final List<Integer> result = new ArrayList<>();
        int number = base10Number;
        while (number != 0) {
            result.add(number % newBase);
            number /= newBase;
        }

        return result.reversed().stream().mapToInt(i -> i).toArray();
    }
}
