import java.util.ArrayList;
import java.util.List;

public final class Prism {
    private static final double TOLERANCE = 0.01;

    private Prism() {}

    public static List<Integer> findSequence(final LaserInfo laser, final List<PrismInfo> prisms) {
        final List<Integer> result = new ArrayList<>();
        findSequence(laser, prisms, result);
        return result;
    }

    public static void findSequence(
            final LaserInfo laser, final List<PrismInfo> prisms, final List<Integer> result) {
        final double radians = Math.toRadians(laser.angle());
        final double dx = Math.cos(radians);
        final double dy = Math.sin(radians);

        PrismInfo closest = null;
        double closestDist = Double.MAX_VALUE;
        for (final PrismInfo prism : prisms) {
            final double vx = prism.x() - laser.x();
            final double vy = prism.y() - laser.y();

            final double crossProduct = dx * vy - dy * vx;
            if (Math.abs(crossProduct) > TOLERANCE) {
                continue;
            }

            final double dotProduct = dx * vx + dy * vy;
            if (dotProduct <= 0) {
                continue;
            }

            final double distanceSq = (vx * vx) + (vy * vy);
            if (distanceSq < closestDist) {
                closestDist = distanceSq;
                closest = prism;
            }
        }

        if (closest == null) {
            return;
        }

        result.add(closest.id());

        findSequence(
                new LaserInfo(closest.x(), closest.y(), laser.angle() + closest.angle()),
                prisms,
                result);
    }

    public record LaserInfo(double x, double y, double angle) {}

    public record PrismInfo(int id, double x, double y, double angle) {}
}
