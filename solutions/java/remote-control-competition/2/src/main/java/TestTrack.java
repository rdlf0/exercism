import java.util.Collections;
import java.util.List;

final class TestTrack {

    private TestTrack() {}

    public static void race(final RemoteControlCar car) {
        car.drive();
    }

    public static List<ProductionRemoteControlCar> getRankedCars(
            final List<ProductionRemoteControlCar> cars) {
        Collections.sort(cars);
        return cars;
    }
}
