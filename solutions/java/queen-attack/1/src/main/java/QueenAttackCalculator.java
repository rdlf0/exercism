class QueenAttackCalculator {

    private Queen whiteQueen;
    private Queen blackQueen;

    public QueenAttackCalculator(final Queen whiteQueen, final Queen blackQueen) {
        validateQueens(whiteQueen, blackQueen);

        this.whiteQueen = whiteQueen;
        this.blackQueen = blackQueen;
    }

    boolean canQueensAttackOneAnother() {
        return whiteQueen.getRow() == blackQueen.getRow() ||
            whiteQueen.getCol() == blackQueen.getCol() ||
            Math.abs(whiteQueen.getCol() - blackQueen.getCol()) == 
            Math.abs(whiteQueen.getRow() - blackQueen.getRow());
    }

    private static void validateQueens(final Queen whiteQueen, final Queen blackQueen) {
        if (whiteQueen == null || blackQueen == null) {
            throw new IllegalArgumentException("You must supply valid positions for both Queens.");
        }

        if (whiteQueen.getRow() == blackQueen.getRow() &&
            whiteQueen.getCol() == blackQueen.getCol()) {
            throw new IllegalArgumentException("Queens cannot occupy the same position.");
        }
    }
}
