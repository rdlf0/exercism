import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class FlowerFieldBoard {
    private static final int[][] DIRECTIONS =
            new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    private final List<String> board;

    FlowerFieldBoard(final List<String> boardRows) {
        this.board = boardRows;
    }

    List<String> withNumbers() {
        final int rows = board.size();
        if (rows == 0) {
            return Collections.emptyList();
        }
        final int cols = board.getFirst().length();
        if (cols == 0) {
            return Collections.singletonList("");
        }

        final List<String> result = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            final StringBuilder sb = new StringBuilder();
            for (int col = 0; col < cols; col++) {
                final char current = board.get(row).charAt(col);
                if (current == '*') {
                    sb.append(current);
                    continue;
                }

                final int count = countAdjacentFlowers(row, col, rows, cols);
                if (count == 0) {
                    sb.append(current);
                } else {
                    sb.append(count);
                }
            }
            result.add(sb.toString());
        }

        return result;
    }

    private int countAdjacentFlowers(final int row, final int col, final int rows, final int cols) {
        int count = 0;
        for (final int[] dir : DIRECTIONS) {
            final int newRow = row + dir[0];
            final int newCol = col + dir[1];

            if (newRow < 0 || newRow == rows || newCol < 0 || newCol == cols) {
                continue;
            }

            if (board.get(newRow).charAt(newCol) == '*') {
                count++;
            }
        }
        return count;
    }
}
