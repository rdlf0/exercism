class Matrix {

    private final int[][] matrix;
    private final int[][] transposed;

    Matrix(final String matrixAsString) {
        final String[] rows = matrixAsString.split("\n");
        final String[] firstRowCols = rows[0].split(" ");

        matrix = new int[rows.length][firstRowCols.length];
        transposed = new int[firstRowCols.length][rows.length];

        for (int i = 0; i < rows.length; i++) {
            final String[] cols = rows[i].split(" ");

            for (int j = 0; j < cols.length; j++) {
                final int col = Integer.parseInt(cols[j]);
                matrix[i][j] = col;
                transposed[j][i] = col;
            }
        }
    }

    int[] getRow(final int rowNumber) {
        return matrix[rowNumber - 1];
    }

    int[] getColumn(final int columnNumber) {
        return transposed[columnNumber - 1];
    }

}
