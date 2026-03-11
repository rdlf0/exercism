import java.util.Arrays;

class Darts {

    private double x;
    private double y;
    private static final Integer[] radiusRanges = {1, 5, 10};

    Darts(double x, double y) {
        this.x = x;
        this.y = y;
    }

    int score() {
        double dist = this.getDistanceFromCenter();

        for (int radius : radiusRanges) {
            if (dist <= radius) {
                int index = Arrays.asList(radiusRanges).indexOf(radius);
                int len = radiusRanges.length;
                return radiusRanges[len - index - 1];
            }
        }

        return 0;
    }

    private double getDistanceFromCenter() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

}
