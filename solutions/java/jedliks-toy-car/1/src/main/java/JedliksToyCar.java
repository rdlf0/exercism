public class JedliksToyCar {
    private int distance = 0;
    private int battery = 100;

    public static JedliksToyCar buy() {
        return new JedliksToyCar();
    }

    public String distanceDisplay() {
        return "Driven %d meters".formatted(distance);
    }

    public String batteryDisplay() {
        return battery < 0 ? "Battery empty" : "Battery at %d%%".formatted(battery);
    }

    public void drive() {
        if (battery > 0) {
            distance += 20;
        }
        battery--;
    }
}
