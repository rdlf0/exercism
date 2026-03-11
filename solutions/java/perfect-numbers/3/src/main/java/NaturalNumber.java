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
        int aliquotSum = this.calculateAliquotSum();

        if (aliquotSum < number || aliquotSum == 1) {
            return Classification.DEFICIENT;
        } else if (aliquotSum > number) {
            return Classification.ABUNDANT;
        } else {
            return Classification.PERFECT;
        }
    }

    private int calculateAliquotSum() {
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

        return aliquotSum + 1;
    }

}
