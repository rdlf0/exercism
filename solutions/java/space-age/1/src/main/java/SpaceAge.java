class SpaceAge {

    private static final double OP_EARTH = 31557600;
    private static final double OP_MERCURY = 0.2408467;
    private static final double OP_VENUS = 0.61519726;
    private static final double OP_MARS = 1.8808158;
    private static final double OP_JUPITER = 11.862615;
    private static final double OP_SATURN = 29.447498;
    private static final double OP_URANUS = 84.016846;
    private static final double OP_NEPTUNE = 164.79132;

    private double seconds;

    SpaceAge(double seconds) {
        this.seconds = seconds;
    }

    private double getSeconds() {
        return this.seconds;
    }

    double onEarth() {
        return this.getSeconds() / OP_EARTH;
    }

    double onMercury() {
        return this.getSeconds() / (OP_EARTH * OP_MERCURY);
    }

    double onVenus() {
        return this.getSeconds() / (OP_EARTH * OP_VENUS);
    }

    double onMars() {
        return this.getSeconds() / (OP_EARTH * OP_MARS);
    }

    double onJupiter() {
        return this.getSeconds() / (OP_EARTH * OP_JUPITER);
    }

    double onSaturn() {
        return this.getSeconds() / (OP_EARTH * OP_SATURN);
    }

    double onUranus() {
        return this.getSeconds() / (OP_EARTH * OP_URANUS);
    }

    double onNeptune() {
        return this.getSeconds() / (OP_EARTH * OP_NEPTUNE);
    }

}
