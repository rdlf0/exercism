import java.util.ArrayList;
import java.util.List;

class CryptoSquare {
    private final String normalized;

    CryptoSquare(final String plaintext) {
        normalized =
                plaintext
                        .chars()
                        .mapToObj(ch -> (char) ch)
                        .filter(Character::isLetterOrDigit)
                        .map(Character::toLowerCase)
                        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                        .toString();
    }

    String getCiphertext() {
        if (normalized.isEmpty()) {
            return "";
        }

        final int len = normalized.length();
        final int cols = (int) Math.ceil(Math.sqrt(len));
        final int rows = Math.ceilDiv(len, cols);
        final char[][] grid = new char[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                final int charIndex = row * cols + col;
                grid[row][col] =
                        charIndex < normalized.length() ? normalized.charAt(charIndex) : ' ';
            }
        }

        final List<String> result = new ArrayList<>();
        for (int col = 0; col < cols; col++) {
            final StringBuilder colBuilder = new StringBuilder();
            for (int row = 0; row < rows; row++) {
                colBuilder.append(grid[row][col]);
            }
            result.add(colBuilder.toString());
        }

        return String.join(" ", result);
    }
}
