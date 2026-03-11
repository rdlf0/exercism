class NaturalNumber {

    private static final String ERROR_NON_POSITIVE =
            "You must supply a natural number (positive integer)";

    private int number;

    NaturalNumber(final int number) {
        if (number < 1) {
            throw new IllegalArgumentException(ERROR_NON_POSITIVE);
        }

        this.number = number;
    }

    Classification getClassification() {
        int aliquotSum = 0;

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                if (i == number / i) {
                    aliquotSum += i;
                } else {
                    aliquotSum += (i + number / i);
                }
            }
        }

        // Prime number
        if (aliquotSum == 0) {
            return Classification.DEFICIENT;
        }

        aliquotSum++;

        if (aliquotSum == number) {
            return Classification.PERFECT;
        } else if (aliquotSum > number) {
            return Classification.ABUNDANT;
        } else {
            return Classification.DEFICIENT;
        }
    }

}
