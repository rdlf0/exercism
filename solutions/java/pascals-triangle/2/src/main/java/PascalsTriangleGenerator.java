class PascalsTriangleGenerator {
    int[][] generateTriangle(final int rows) {
        final int[][] result = new int[rows][];
        for (int row = 0; row < rows; row++) {
            result[row] = new int[row + 1];
            result[row][0] = 1;
            result[row][row] = 1;
            for (int col = 1; col < row; col++) {
                result[row][col] = result[row - 1][col] + result[row - 1][col - 1];
            }
        }

        return result;
    }
}
