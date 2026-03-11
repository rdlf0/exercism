import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

class ResistorColorTrio {
    private static final Map<String, Integer> VALUES_BY_COLOR =
            Map.of(
                    "black", 0,
                    "brown", 1,
                    "red", 2,
                    "orange", 3,
                    "yellow", 4,
                    "green", 5,
                    "blue", 6,
                    "violet", 7,
                    "grey", 8,
                    "white", 9);

    private static final NavigableMap<Long, String> METRIC_PREFIXES_BY_THOUSANDS =
            new TreeMap<>(
                    Map.of(
                            1L, "",
                            1_000L, "kilo",
                            1_000_000L, "mega",
                            1_000_000_000L, "giga"));

    String label(final String[] colors) {
        long value =
                (10L * VALUES_BY_COLOR.get(colors[0]) + VALUES_BY_COLOR.get(colors[1]))
                        * (long) Math.pow(10, VALUES_BY_COLOR.get(colors[2]));

        final Long scale = METRIC_PREFIXES_BY_THOUSANDS.floorKey(value);
        value = scale == null ? 0 : value / scale;

        final String prefix = scale == null ? "" : METRIC_PREFIXES_BY_THOUSANDS.get(scale);

        return "%d %sohms".formatted(value, prefix);
    }
}
