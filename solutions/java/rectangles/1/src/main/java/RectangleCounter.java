import java.util.ArrayList;
import java.util.List;

class RectangleCounter {
    private static boolean isRowValid(
            final String[] board, final int row, final int colFrom, final int colTo) {
        for (int col = colFrom; col <= colTo; col++) {
            if (board[row].charAt(col) != '-' && board[row].charAt(col) != '+') {
                return false;
            }
        }
        return true;
    }

    private static boolean isColValid(
            final String[] board, final int col, final int rowFrom, final int rowTo) {
        for (int row = rowFrom; row <= rowTo; row++) {
            if (board[row].charAt(col) != '|' && board[row].charAt(col) != '+') {
                return false;
            }
        }
        return true;
    }

    int countRectangles(final String[] grid) {
        if (grid.length == 0 || grid[0].isBlank()) {
            return 0;
        }

        final int rows = grid.length;
        final int cols = grid[0].length();
        final List<Point> pluses = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == '+') {
                    pluses.add(new Point(row, col));
                }
            }
        }

        int count = 0;
        for (final Point start : pluses) {
            if (start.row == rows - 1 || start.col == cols - 1) {
                continue;
            }
            for (final Point end : pluses) {
                if (start.row >= end.row || start.col >= end.col) {
                    continue;
                }
                if (isRowValid(grid, start.row, start.col, end.col)
                        && isRowValid(grid, end.row, start.col, end.col)
                        && isColValid(grid, start.col, start.row, end.row)
                        && isColValid(grid, end.col, start.row, end.row)) {
                    count++;
                }
            }
        }

        return count;
    }

    private record Point(int row, int col) {}
}
