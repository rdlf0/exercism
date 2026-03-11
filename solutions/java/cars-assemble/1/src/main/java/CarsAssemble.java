import java.util.List;

public class CarsAssemble {

    private static final int PER_HOUR = 221;
    private static final List<Integer> SUCCESS_RATES =
            List.of(0, 100, 100, 100, 100, 90, 90, 90, 90, 80, 77);

    public double productionRatePerHour(final int speed) {
        return speed * PER_HOUR * SUCCESS_RATES.get(speed) / 100.0;
    }

    public int workingItemsPerMinute(final int speed) {
        return (int) this.productionRatePerHour(speed) / 60;
    }
}
