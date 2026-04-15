import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.BiPredicate;

class Connect {
    private static final int[][] DIRECTIONS = {{-1, -1}, {-1, 1}, {0, -2}, {0, 2}, {1, -1}, {1, 1}};
    private static final char PLAYER_O_CHAR = 'O';
    private static final char PLAYER_X_CHAR = 'X';
    private static final char OUTSIDE_BOARD_CHAR = '_';

    private final char[][] board;
    private final int rows;
    private final int cols;
    private final BiPredicate<Integer, Integer> oWinPredicate;
    private final BiPredicate<Integer, Integer> xWinPredicate;
    private Winner winner = null;

    public Connect(final String[] board) {
        this.rows = board.length;
        this.cols = board[0].length() + rows - 1;
        this.board = new char[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (col >= board[row].length() || col < row) {
                    this.board[row][col] = OUTSIDE_BOARD_CHAR;
                } else {
                    this.board[row][col] = board[row].charAt(col);
                }
            }
        }

        this.oWinPredicate = (r, _) -> r == rows - 1;
        this.xWinPredicate = (r, c) -> c == board[r].length() - 1;
    }

    public Winner computeWinner() {
        if (winner != null) {
            return winner;
        }

        for (int col = 0; col < cols; col += 2) {
            if (board[0][col] == PLAYER_O_CHAR
                    && checkPlayer(PLAYER_O_CHAR, 0, col, oWinPredicate)) {
                winner = Winner.PLAYER_O;
                break;
            }
        }

        for (int row = 0; row < rows; row++) {
            if (board[row][row] == PLAYER_X_CHAR
                    && checkPlayer(PLAYER_X_CHAR, row, row, xWinPredicate)) {
                winner = Winner.PLAYER_X;
                break;
            }
        }

        if (winner == null) {
            winner = Winner.NONE;
        }

        return winner;
    }

    private boolean checkPlayer(
            final char search,
            final int row,
            final int col,
            final BiPredicate<Integer, Integer> winPredicate) {
        final Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {row, col});

        while (!queue.isEmpty()) {
            final int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                final int[] current = queue.poll();
                if (current == null) {
                    return false;
                }
                final int currentRow = current[0];
                final int currentCol = current[1];
                if (winPredicate.test(currentRow, currentCol)) {
                    return true;
                }

                // Mark as visited
                board[currentRow][currentCol] = Character.toLowerCase(search);

                for (final int[] dir : DIRECTIONS) {
                    final int newRow = currentRow + dir[0];
                    final int newCol = currentCol + dir[1];

                    if (newRow < 0
                            || newRow >= board.length
                            || newCol < 0
                            || newCol >= board[newRow].length
                            || board[newRow][newCol] != search) {
                        continue;
                    }

                    queue.offer(new int[] {newRow, newCol});
                }
            }
        }

        return false;
    }
}
