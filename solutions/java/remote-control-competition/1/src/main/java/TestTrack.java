import java.util.Comparator;
import java.util.List;

final class TestTrack {

    private TestTrack() {}

    public static void race(final RemoteControlCar car) {
        car.drive();
    }

    public static List<ProductionRemoteControlCar> getRankedCars(
            final List<ProductionRemoteControlCar> cars) {
        cars.sort(
                Comparator.comparingInt(ProductionRemoteControlCar::getNumberOfVictories)
                        .reversed());
        return cars;
    }
}
