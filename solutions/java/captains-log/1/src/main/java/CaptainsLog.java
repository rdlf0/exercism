import java.util.Random;

class CaptainsLog {
    private static final String SHIP_NUMBER_PREFIX = "NCC";
    private static final int SHIP_NUMBER_LOWER_BOUND = 1000;
    private static final int SHIP_NUMBER_UPPER_BOUND = 9999;
    private static final double STARDATE_LOWER_BOUND = 41000.0;
    private static final double STARDATE_UPPER_BOUND = 42000.0;
    private static final char[] PLANET_CLASSES =
            new char[] {'D', 'H', 'J', 'K', 'L', 'M', 'N', 'R', 'T', 'Y'};

    private final Random random;

    CaptainsLog(final Random random) {
        this.random = random;
    }

    char randomPlanetClass() {
        return PLANET_CLASSES[random.nextInt(PLANET_CLASSES.length)];
    }

    String randomShipRegistryNumber() {
        return "%s-%d"
                .formatted(
                        SHIP_NUMBER_PREFIX,
                        SHIP_NUMBER_LOWER_BOUND
                                + random.nextInt(
                                        SHIP_NUMBER_UPPER_BOUND - SHIP_NUMBER_LOWER_BOUND + 1));
    }

    double randomStardate() {
        return STARDATE_LOWER_BOUND
                + ((STARDATE_UPPER_BOUND - STARDATE_LOWER_BOUND) * random.nextDouble());
    }
}
