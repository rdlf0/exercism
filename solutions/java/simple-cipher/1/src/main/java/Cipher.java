import java.util.Random;
import java.util.function.BiFunction;

public class Cipher {
    private static final BiFunction<Character, Character, Character> ENCODER =
            (a, b) -> (char) ((a - 'a' + b - 'a') % 26 + 'a');
    private static final BiFunction<Character, Character, Character> DECODER =
            (a, b) -> (char) ((a - b + 26) % 26 + 'a');

    private final String key;

    public Cipher() {
        this.key =
                new Random()
                        .ints(100, 'a', 'z' + 1)
                        .mapToObj(ch -> (char) ch)
                        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                        .toString();
    }

    public Cipher(final String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String encode(final String plainText) {
        return this.convert(plainText, ENCODER);
    }

    public String decode(final String cipherText) {
        return this.convert(cipherText, DECODER);
    }

    private String convert(
            final String input, final BiFunction<Character, Character, Character> converter) {
        final int keyLen = key.length();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            final char inputCh = input.charAt(i);
            final char keyCh = key.charAt(i % keyLen);
            final char encoded = converter.apply(inputCh, keyCh);
            sb.append(encoded);
        }

        return sb.toString();
    }
}
