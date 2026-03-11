import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class VariableLengthQuantity {

    private static final int SEVEN_BIT_MASK = 0b01111111;
    private static final int MOST_SIGNIFICANT_BIT = 0b10000000;
    private static final String INVALID_BYTES_MESSAGE = "Invalid variable-length quantity encoding";

    List<String> encode(final List<Long> numbers) {
        return numbers.stream()
            .map(this::encode)
            .flatMap(List::stream)
            .collect(Collectors.toList());
    }

    private List<String> encode(Long number) {
        final List<String> result = new ArrayList<>();

        if (number == 0) {
            result.add(toHex(0L));
            return result;
        }

        while (number > 0) {
            long group = number & SEVEN_BIT_MASK;
            number >>= 7;

            if (!result.isEmpty()) {
                group |= MOST_SIGNIFICANT_BIT;
            }

            result.add(0, toHex(group));
        }

        return result;
    }

    List<String> decode(final List<Long> bytes) {
        validateEncodedBytes(bytes);

        final List<String> result = new ArrayList<>();
        long number = 0;
        for (final Long b : bytes) {
            number |= (b & SEVEN_BIT_MASK);

            if ((b & MOST_SIGNIFICANT_BIT) == MOST_SIGNIFICANT_BIT) {
                number <<= 7;
            } else {
                result.add(toHex(number));
                number = 0;
            }
        }

        return result;
    }

    private void validateEncodedBytes(final List<Long> bytes) {
        final Long lastByte = bytes.get(bytes.size() - 1);
        if ((lastByte & MOST_SIGNIFICANT_BIT) == MOST_SIGNIFICANT_BIT) {
            throw new IllegalArgumentException(INVALID_BYTES_MESSAGE);
        }
    }

    private static String toHex(final Long number) {
        return String.format("0x%x", number);
    }
}
