import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Matrix {
    private final List<List<Integer>> matrix;

    Matrix(final List<List<Integer>> values) {
        this.matrix = values;
    }

    Set<MatrixCoordinate> getSaddlePoints() {
        final Set<MatrixCoordinate> result = new HashSet<>();

        for (int row = 0; row < matrix.size(); row++) {
            final List<Integer> maxCols = new ArrayList<>();
            int maxVal = -1;
            for (int col = 0; col < matrix.get(row).size(); col++) {
                final int currentVal = matrix.get(row).get(col);
                if (currentVal >= maxVal) {
                    if (currentVal > maxVal) {
                        maxVal = currentVal;
                        maxCols.clear();
                    }
                    maxCols.add(col);
                }
            }

            findSmallerInCurrentColumn:
            for (final int col : maxCols) {
                for (int columnRow = 0; columnRow < matrix.size(); columnRow++) {
                    if (columnRow == row) {
                        continue;
                    }
                    if (matrix.get(columnRow).get(col) < matrix.get(row).get(col)) {
                        break findSmallerInCurrentColumn;
                    }
                }
                result.add(new MatrixCoordinate(row + 1, col + 1));
            }
        }

        return result;
    }
}
