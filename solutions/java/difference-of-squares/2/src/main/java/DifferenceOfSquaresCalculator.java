class DifferenceOfSquaresCalculator {

    int computeSquareOfSumTo(final int input) {
        final int sum = input * (input + 1) / 2;

        return sum * sum;
    }

    int computeSumOfSquaresTo(final int input) {
        return input * (input + 1) * (2 * input + 1) / 6;
    }

    int computeDifferenceOfSquares(final int input) {
        return this.computeSquareOfSumTo(input) - this.computeSumOfSquaresTo(input);
    }

}
