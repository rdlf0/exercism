class ProductionRemoteControlCar
        implements RemoteControlCar, Comparable<ProductionRemoteControlCar> {
    private static final int DISTANCE_PER_DRIVE = 10;
    private int distanceTravelled = 0;
    private int numberOfVictories = 0;

    public void drive() {
        distanceTravelled += DISTANCE_PER_DRIVE;
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public int getNumberOfVictories() {
        return numberOfVictories;
    }

    public void setNumberOfVictories(final int numberOfVictories) {
        this.numberOfVictories = numberOfVictories;
    }

    @Override
    public int compareTo(final ProductionRemoteControlCar productionRemoteControlCar) {
        return productionRemoteControlCar.getNumberOfVictories() - this.getNumberOfVictories();
    }
}
