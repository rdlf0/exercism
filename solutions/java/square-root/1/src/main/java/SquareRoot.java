public class SquareRoot {
    public int squareRoot(final int radicand) {
        int left = 1;
        int right = radicand;
        while (left < right) {
            final int middle = (left + right) / 2;
            final int middleSq = middle * middle;

            if (middleSq > radicand) {
                right = middle + 1;
            } else if (middleSq < radicand) {
                left = middle - 1;
            } else {
                return middle;
            }
        }

        return 1;
    }
}
