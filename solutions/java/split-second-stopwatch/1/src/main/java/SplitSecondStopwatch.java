import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SplitSecondStopwatch {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final List<String> previousLaps = new ArrayList<>();
    private LocalTime currentLap = LocalTime.MIN;
    private LocalTime totalTime = LocalTime.MIN;
    private State state = State.READY;

    public void start() {
        if (state == State.RUNNING) {
            throw new IllegalStateException("cannot start an already running stopwatch");
        }

        state = State.RUNNING;
    }

    public void stop() {
        if (state != State.RUNNING) {
            throw new IllegalStateException("cannot stop a stopwatch that is not running");
        }

        state = State.STOPPED;
    }

    public void reset() {
        if (state != State.STOPPED) {
            throw new IllegalStateException("cannot reset a stopwatch that is not stopped");
        }

        state = State.READY;
        currentLap = LocalTime.MIN;
        previousLaps.clear();
    }

    public void lap() {
        if (state != State.RUNNING) {
            throw new IllegalStateException("cannot lap a stopwatch that is not running");
        }

        previousLaps.add(this.currentLap());
        currentLap = LocalTime.MIN;
    }

    public String state() {
        return state.toString();
    }

    public String currentLap() {
        return currentLap.format(TIME_FORMATTER);
    }

    public String total() {
        return totalTime.format(TIME_FORMATTER);
    }

    public List<String> previousLaps() {
        return previousLaps;
    }

    public void advanceTime(final String timeString) {
        if (state == State.STOPPED) {
            return;
        }

        final LocalTime shiftTime = LocalTime.parse(timeString, TIME_FORMATTER);
        final Duration duration = Duration.between(LocalTime.MIN, shiftTime);
        currentLap = currentLap.plus(duration);
        totalTime = totalTime.plus(duration);
    }
}
