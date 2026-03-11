import java.util.concurrent.atomic.AtomicInteger;

class Robot {
    private static final AtomicInteger GLOBAL_COUNTER = new AtomicInteger(0);
    private static final int MAX_POOL = 26 * 26 * 1000;
    private static final int MULTIPLIER = 144301;
    private static final int INCREMENT = 1;

    private String name;

    public Robot() {
        reset();
    }

    String getName() {
        return name;
    }

    void reset() {
        final int index = GLOBAL_COUNTER.getAndIncrement();
        if (index >= MAX_POOL) {
            GLOBAL_COUNTER.set(0);
        }

        final long scramble = ((long) index * MULTIPLIER + INCREMENT) % MAX_POOL;

        final int lettersPart = (int) (scramble / 1000);
        final int digitsPart = (int) (scramble % 1000);

        final char ch1 = (char) ('A' + (lettersPart / 26));
        final char ch2 = (char) ('A' + (lettersPart % 26));

        name = "%c%c%03d".formatted(ch1, ch2, digitsPart);
    }
}
