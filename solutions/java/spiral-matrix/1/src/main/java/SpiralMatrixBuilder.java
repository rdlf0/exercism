class SpiralMatrixBuilder {
    int[][] buildMatrixOfSize(final int size) {
        final int[][] matrix = new int[size][size];
        if (size == 0) {
            return matrix;
        }

        int left = 0;
        int right = size - 1;
        int top = 0;
        int bottom = size - 1;
        int val = 1;

        while (left <= right) {
            for (int col = left; col <= right; col++) {
                matrix[top][col] = val++;
            }
            top++;

            for (int row = top; row <= bottom; row++) {
                matrix[row][right] = val++;
            }
            right--;

            for (int col = right; col >= left; col--) {
                matrix[bottom][col] = val++;
            }
            bottom--;

            for (int row = bottom; row >= top; row--) {
                matrix[row][left] = val++;
            }
            left++;
        }

        return matrix;
    }
}
