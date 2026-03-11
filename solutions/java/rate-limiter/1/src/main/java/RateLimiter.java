import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class RateLimiter<K> {
    private final int limit;
    private final Duration windowSize;
    private final TimeSource timeSource;

    private final Map<K, Window> limits = new HashMap<>();

    public RateLimiter(final int limit, final Duration windowSize, final TimeSource timeSource) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.timeSource = timeSource;
    }

    public boolean allow(final K clientId) {
        limits.compute(
                clientId,
                (_, window) -> {
                    if (window == null || !window.getEnd().isAfter(timeSource.now())) {
                        return new Window(timeSource.now().plus(windowSize), limit);
                    } else {
                        return window.reduceAttempts();
                    }
                });

        return limits.get(clientId).getAvailableAttempts() > 0;
    }

    private static class Window {
        private final Instant end;
        private int availableAttempts;

        public Window(final Instant end, final int availableAttempts) {
            this.end = end;
            this.availableAttempts = availableAttempts;
        }

        public Instant getEnd() {
            return end;
        }

        public int getAvailableAttempts() {
            return availableAttempts;
        }

        Window reduceAttempts() {
            availableAttempts--;
            return this;
        }
    }
}
