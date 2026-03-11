class PascalsTriangleGenerator {
    int[][] generateTriangle(final int rows) {
        final int[][] result = new int[rows][];
        if (rows > 0) {
            result[0] = new int[] {1};
        }

        for (int row = 1; row < rows; row++) {
            result[row] = new int[row + 1];
            for (int col = 0; col < row + 1; col++) {
                final int a = col == row ? 0 : result[row - 1][col];
                final int b = col == 0 ? 0 : result[row - 1][col - 1];
                result[row][col] = a + b;
            }
        }

        return result;
    }
}
