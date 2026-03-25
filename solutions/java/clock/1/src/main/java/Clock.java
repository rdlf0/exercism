import java.time.LocalTime;
import java.util.Objects;

class Clock {
    private LocalTime time;

    Clock(final int hours, final int minutes) {
        time = LocalTime.MIDNIGHT.plusHours(hours).plusMinutes(minutes);
    }

    void add(final int minutes) {
        time = time.plusMinutes(minutes);
    }

    @Override
    public String toString() {
        return time.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Clock clock)) {
            return false;
        }
        return Objects.equals(time, clock.time);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(time);
    }
}
