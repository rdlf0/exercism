class Queen {

    private static final int MIN_POSITION_VALUE = 0;
    private static final int MAX_POSITION_VALUE = 7;

    private int row;
    private int col;

    public Queen(final int row, final int col) {
        validatePosition(row, col);

        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    private static void validatePosition(final int row, final int col) {
        if (row < MIN_POSITION_VALUE) {
            throw new IllegalArgumentException("Queen position must have positive row.");
        }

        if (row > MAX_POSITION_VALUE) {
            throw new IllegalArgumentException("Queen position must have row <= 7.");
        }

        if (col < MIN_POSITION_VALUE) {
            throw new IllegalArgumentException("Queen position must have positive column.");
        }

        if (col > MAX_POSITION_VALUE) {
            throw new IllegalArgumentException("Queen position must have column <= 7.");
        }
    }

}
