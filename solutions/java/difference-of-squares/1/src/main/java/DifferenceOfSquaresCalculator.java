class DifferenceOfSquaresCalculator {

    int computeSquareOfSumTo(final int input) {
        int sum;

        if (input % 2 == 0) {
            sum = input / 2 * (input + 1);
        } else {
            sum = (input + 1) / 2 * input;
        }

        return (int) Math.pow(sum, 2);
    }

    int computeSumOfSquaresTo(final int input) {
        return input * (input + 1) * (2 * input + 1) / 6;
    }

    int computeDifferenceOfSquares(final int input) {
        return this.computeSquareOfSumTo(input) - this.computeSumOfSquaresTo(input);
    }

}
