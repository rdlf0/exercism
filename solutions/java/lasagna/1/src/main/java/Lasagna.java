public class Lasagna {
    private static final int TOTAL_MINUTES = 40;

    public int expectedMinutesInOven() {
        return TOTAL_MINUTES;
    }

    public int remainingMinutesInOven(final int minutesPassed) {
        return this.expectedMinutesInOven() - minutesPassed;
    }

    public int preparationTimeInMinutes(final int numLayers) {
        return numLayers * 2;
    }

    public int totalTimeInMinutes(final int numLayers, final int minutesPassed) {
        return preparationTimeInMinutes(numLayers) + minutesPassed;
    }
}
