import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class MinesweeperBoard {

    private final char[][] board;

    MinesweeperBoard(final List<String> board) {
        this.board = board.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    List<String> withNumbers() {
        return IntStream.range(0, board.length)
                .mapToObj(row -> IntStream.range(0, board[row].length)
                        .mapToObj(col -> this.calculateValue(row, col))
                        .collect(
                                StringBuilder::new,
                                StringBuilder::append,
                                StringBuilder::append)
                        .toString())
                .collect(Collectors.toList());
    }

    private char calculateValue(final int row, final int col) {
        if (board[row][col] == '*') {
            return board[row][col];
        }

        final int count = this.getAdjacentMinesCount(row, col);

        if (count == 0) {
            return board[row][col];
        }

        return Character.forDigit(count, 10);
    }

    private int getAdjacentMinesCount(final int row, final int col) {
        int count = 0;

        for (int i = Math.max(0, row - 1); i < Math.min(board.length, row + 2); i++) {
            for (int j = Math.max(0, col - 1); j < Math.min(board[0].length, col + 2); j++) {
                if (board[i][j] == '*') {
                    count++;
                }
            }
        }

        return count;
    }

}
