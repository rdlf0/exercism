import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

class Matrix {
    private final int rows;
    private final int cols;
    private final int[] rowsMax;
    private final int[] colsMin;

    Matrix(final List<List<Integer>> values) {
        this.rows = values.size();
        this.cols = rows > 0 ? values.getFirst().size() : 0;
        this.rowsMax =
                IntStream.range(0, rows)
                        .map(
                                row ->
                                        IntStream.range(0, cols)
                                                .map(col -> values.get(row).get(col))
                                                .max()
                                                .orElse(-1))
                        .toArray();

        this.colsMin =
                IntStream.range(0, cols)
                        .map(
                                col ->
                                        IntStream.range(0, rows)
                                                .map(row -> values.get(row).get(col))
                                                .min()
                                                .orElse(-1))
                        .toArray();
    }

    Set<MatrixCoordinate> getSaddlePoints() {
        final Set<MatrixCoordinate> result = new HashSet<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (rowsMax[row] == colsMin[col]) {
                    result.add(new MatrixCoordinate(row + 1, col + 1));
                }
            }
        }

        return result;
    }
}
