import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

final class OpticalCharacterReader {
    private static final Map<Set<Segment>, Integer> DIGITS_BY_SEGMENTS =
            Map.of(
                    EnumSet.of(
                                    Segment.TOP,
                                    Segment.BOTTOM,
                                    Segment.TOP_LEFT,
                                    Segment.TOP_RIGHT,
                                    Segment.BOTTOM_LEFT,
                                    Segment.BOTTOM_RIGHT),
                            0,
                    EnumSet.of(Segment.TOP_RIGHT, Segment.BOTTOM_RIGHT), 1,
                    EnumSet.of(
                                    Segment.TOP,
                                    Segment.TOP_RIGHT,
                                    Segment.MIDDLE,
                                    Segment.BOTTOM_LEFT,
                                    Segment.BOTTOM),
                            2,
                    EnumSet.of(
                                    Segment.TOP,
                                    Segment.TOP_RIGHT,
                                    Segment.MIDDLE,
                                    Segment.BOTTOM_RIGHT,
                                    Segment.BOTTOM),
                            3,
                    EnumSet.of(
                                    Segment.TOP_LEFT,
                                    Segment.MIDDLE,
                                    Segment.TOP_RIGHT,
                                    Segment.BOTTOM_RIGHT),
                            4,
                    EnumSet.of(
                                    Segment.TOP,
                                    Segment.TOP_LEFT,
                                    Segment.MIDDLE,
                                    Segment.BOTTOM_RIGHT,
                                    Segment.BOTTOM),
                            5,
                    EnumSet.of(
                                    Segment.TOP,
                                    Segment.TOP_LEFT,
                                    Segment.MIDDLE,
                                    Segment.BOTTOM_RIGHT,
                                    Segment.BOTTOM,
                                    Segment.BOTTOM_LEFT),
                            6,
                    EnumSet.of(Segment.TOP, Segment.TOP_RIGHT, Segment.BOTTOM_RIGHT), 7,
                    EnumSet.allOf(Segment.class), 8,
                    EnumSet.of(
                                    Segment.TOP,
                                    Segment.TOP_LEFT,
                                    Segment.TOP_RIGHT,
                                    Segment.MIDDLE,
                                    Segment.BOTTOM_RIGHT,
                                    Segment.BOTTOM),
                            9);

    private OpticalCharacterReader() {}

    static String parse(final List<String> input) {
        validate(input);

        final StringBuilder sb = new StringBuilder();
        final int rows = input.size() / 4;
        final int cols = input.getFirst().length() / 3;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                parseDigit(input, row, col, sb);
            }

            if (row < rows - 1) {
                sb.append(",");
            }
        }

        return sb.toString();
    }

    private static void parseDigit(
            final List<String> input, final int row, final int col, final StringBuilder sb) {
        final Set<Segment> segments = EnumSet.noneOf(Segment.class);
        for (int subRow = 0; subRow < 3; subRow++) {
            for (int subCol = 0; subCol < 3; subCol++) {
                if (subRow == 0 && (subCol == 0 || subCol == 2)) {
                    continue;
                }
                final char ch = input.get(row * 4 + subRow).charAt(col * 3 + subCol);
                Segment.getByRowColAndChar(subRow, subCol, ch).ifPresent(segments::add);
            }
        }

        if (DIGITS_BY_SEGMENTS.containsKey(segments)) {
            sb.append(DIGITS_BY_SEGMENTS.get(segments));
        } else {
            sb.append("?");
        }
    }

    private static void validate(final List<String> input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Empty input");
        }

        if (input.size() % 4 != 0) {
            throw new IllegalArgumentException(
                    "Number of input rows must be a positive multiple of 4");
        }

        if (input.getFirst().isEmpty()) {
            throw new IllegalArgumentException("Empty input");
        }

        for (final String line : input) {
            if (line.length() % 3 != 0) {
                throw new IllegalArgumentException(
                        "Number of input columns must be a positive multiple of 3");
            }
        }
    }

    private enum Segment {
        TOP(0, 1, '_'),
        MIDDLE(1, 1, '_'),
        BOTTOM(2, 1, '_'),
        TOP_LEFT(1, 0, '|'),
        TOP_RIGHT(1, 2, '|'),
        BOTTOM_LEFT(2, 0, '|'),
        BOTTOM_RIGHT(2, 2, '|'),
        ;

        private static final Map<Integer, Map<Integer, Segment>> SEGMENTS_BY_COL_BY_ROW =
                new HashMap<>();

        static {
            for (final Segment s : Segment.values()) {
                SEGMENTS_BY_COL_BY_ROW.computeIfAbsent(s.row, _ -> new HashMap<>()).put(s.col, s);
            }
        }

        private final int row;
        private final int col;
        private final char ch;

        Segment(final int row, final int col, final char ch) {
            this.row = row;
            this.col = col;
            this.ch = ch;
        }

        public static Optional<Segment> getByRowColAndChar(
                final int row, final int col, final char ch) {
            return Optional.of(SEGMENTS_BY_COL_BY_ROW.get(row))
                    .map(r -> r.get(col))
                    .filter(s -> s.ch == ch);
        }
    }
}
