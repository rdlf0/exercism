class NeedForSpeed {
    private static final int NITRO_SPEED = 50;
    private static final int NITRO_BATTERY_DRAIN = 4;

    private final int speed;
    private final int batteryDrain;
    private int batteryLevel = 100;
    private int distanceDriven;

    NeedForSpeed(final int speed, final int batteryDrain) {
        this.speed = speed;
        this.batteryDrain = batteryDrain;
    }

    public static NeedForSpeed nitro() {
        return new NeedForSpeed(NITRO_SPEED, NITRO_BATTERY_DRAIN);
    }

    public int getSpeed() {
        return speed;
    }

    public int getBatteryDrain() {
        return batteryDrain;
    }

    public boolean batteryDrained() {
        return batteryLevel < batteryDrain;
    }

    public int distanceDriven() {
        return distanceDriven;
    }

    public void drive() {
        if (batteryLevel > 0) {
            distanceDriven += speed;
            batteryLevel -= batteryDrain;
        }
    }
}

class RaceTrack {
    private final int distance;

    RaceTrack(final int distance) {
        this.distance = distance;
    }

    public boolean canFinishRace(final NeedForSpeed car) {
        return distance <= 100 / car.getBatteryDrain() * car.getSpeed();
    }
}
