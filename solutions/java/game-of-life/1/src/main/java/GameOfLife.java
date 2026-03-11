class GameOfLife {
    private static final int[][] DIRECTIONS =
            new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public int[][] tick(final int[][] matrix) {
        if (matrix.length == 0) {
            return new int[][] {};
        }

        final int rows = matrix.length;
        final int cols = matrix[0].length;
        final int[][] result = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int aliveNeighbours = 0;
                for (final int[] dir : DIRECTIONS) {
                    final int nRow = row + dir[0];
                    final int nCol = col + dir[1];

                    if (nRow < 0 || nCol < 0 || nRow >= rows || nCol >= cols) {
                        continue;
                    }

                    aliveNeighbours += matrix[nRow][nCol];
                }

                if (matrix[row][col] == 1 && (aliveNeighbours == 2 || aliveNeighbours == 3)) {
                    result[row][col] = 1;
                } else if (matrix[row][col] == 0 && aliveNeighbours == 3) {
                    result[row][col] = 1;
                }
            }
        }

        return result;
    }
}
