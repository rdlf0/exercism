import java.util.stream.Collectors;
import java.util.stream.Gatherers;

public class AffineCipher {
    private static final int ALPHABET_SIZE = 26;
    private static final int ENCODED_CHUNK_SIZE = 5;

    private static char encodeChar(final char ch, final int a, final int b) {
        if (Character.isDigit(ch)) {
            return ch;
        }

        final int x = (ch - 'a') * a + b;
        return (char) (x % ALPHABET_SIZE + 'a');
    }

    private static char decodeChar(final char ch, final int aInverse, final int b) {
        if (Character.isDigit(ch)) {
            return ch;
        }

        final int x = ((ch - 'a' - b) % ALPHABET_SIZE + ALPHABET_SIZE) % ALPHABET_SIZE;
        return (char) (aInverse * x % ALPHABET_SIZE + 'a');
    }

    private static int findInverse(final int a) {
        for (int x = 1; x < ALPHABET_SIZE; x++) {
            if (((a % ALPHABET_SIZE) * (x % ALPHABET_SIZE)) % ALPHABET_SIZE == 1) {
                return x;
            }
        }

        throw new IllegalArgumentException("Error: keyA and alphabet size must be coprime.");
    }

    public String encode(final String text, final int coefficient1, final int coefficient2) {
        if (coefficient1 % 2 == 0) {
            throw new IllegalArgumentException("Error: keyA and alphabet size must be coprime.");
        }

        return text.chars()
                .mapToObj(ch -> (char) ch)
                .filter(Character::isLetterOrDigit)
                .map(Character::toLowerCase)
                .map(ch -> encodeChar(ch, coefficient1, coefficient2))
                .gather(Gatherers.windowFixed(ENCODED_CHUNK_SIZE))
                .map(list -> list.stream().map(String::valueOf).collect(Collectors.joining()))
                .collect(Collectors.joining(" "));
    }

    public String decode(final String text, final int coefficient1, final int coefficient2) {
        final int coefficient1Inverse = findInverse(coefficient1);

        return text.chars()
                .mapToObj(ch -> (char) ch)
                .filter(Character::isLetterOrDigit)
                .map(ch -> decodeChar(ch, coefficient1Inverse, coefficient2))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
