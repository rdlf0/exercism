class Darts {

    private final double x;
    private final double y;
    private static final int[] RANGES = {1, 5, 10};

    Darts(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    int score() {
        final double dist = Math.sqrt(this.x * this.x + this.y * this.y);

        for (int i = 0; i < RANGES.length; i++) {
            if (dist <= RANGES[i]) {
                return RANGES[RANGES.length - i - 1];
            }
        }

        return 0;
    }

}
