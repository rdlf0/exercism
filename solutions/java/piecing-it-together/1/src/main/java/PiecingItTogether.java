import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public final class PiecingItTogether {
    private static final String FORMAT_PORTRAIT = "portrait";
    private static final String FORMAT_LANDSCAPE = "landscape";
    private static final String FORMAT_SQUARE = "square";

    private static final Predicate<JigsawInfo> PIECES_PRESENT = j -> j.getPieces().isPresent();
    private static final Predicate<JigsawInfo> BORDER_PRESENT = j -> j.getBorder().isPresent();
    private static final Predicate<JigsawInfo> INSIDE_PRESENT = j -> j.getInside().isPresent();
    private static final Predicate<JigsawInfo> ROWS_PRESENT = j -> j.getRows().isPresent();
    private static final Predicate<JigsawInfo> COLUMNS_PRESENT = j -> j.getColumns().isPresent();
    private static final Predicate<JigsawInfo> FORMAT_PRESENT = j -> j.getFormat().isPresent();
    private static final Predicate<JigsawInfo> ASPECT_RATIO_PRESENT =
            j -> j.getAspectRatio().isPresent();

    private static final Map<Predicate<JigsawInfo>, UnaryOperator<JigsawInfo>> BUILDERS_BY_DATA =
            Map.of(
                    PIECES_PRESENT.and(ASPECT_RATIO_PRESENT),
                    PiecingItTogether::buildByPiecesAndAspectRatio,
                    ROWS_PRESENT.and(COLUMNS_PRESENT).and(FORMAT_PRESENT),
                    PiecingItTogether::buildByRowsColumnsAndFormat,
                    ROWS_PRESENT.and(FORMAT_PRESENT),
                    PiecingItTogether::buildByRowsAndFormat,
                    INSIDE_PRESENT.and(ASPECT_RATIO_PRESENT),
                    PiecingItTogether::buildByInsideAndAspectRatio,
                    ROWS_PRESENT.and(ASPECT_RATIO_PRESENT),
                    PiecingItTogether::buildByRowsAndAspectRatio,
                    PIECES_PRESENT.and(BORDER_PRESENT).and(FORMAT_PRESENT),
                    PiecingItTogether::buildByPiecesBorderAndFormat);

    private PiecingItTogether() {}

    public static JigsawInfo getCompleteInformation(final JigsawInfo input) {
        for (final Predicate<JigsawInfo> predicate : BUILDERS_BY_DATA.keySet()) {
            if (predicate.test(input)) {
                return BUILDERS_BY_DATA.get(predicate).apply(input);
            }
        }

        throw new IllegalArgumentException("Insufficient data");
    }

    private static String getFormatFromAspectRatio(final Double ratio) {
        return switch (ratio) {
            case final Double r when r < 1 -> FORMAT_PORTRAIT;
            case final Double r when r > 1 -> FORMAT_LANDSCAPE;
            default -> FORMAT_SQUARE;
        };
    }

    private static JigsawInfo buildByPiecesAndAspectRatio(final JigsawInfo input) {
        final int pieces = input.getPieces().orElse(0);
        final double aspectRatio = input.getAspectRatio().orElse(0);
        final int columns = (int) (Math.sqrt(aspectRatio / pieces) * pieces);
        final int rows = pieces / columns;
        final int border = (2 * columns) + (rows - 2) * 2;
        final int inside = pieces - border;
        final String format = getFormatFromAspectRatio(aspectRatio);

        return new JigsawInfo.Builder()
                .pieces(pieces)
                .border(border)
                .inside(inside)
                .rows(rows)
                .columns(columns)
                .aspectRatio(aspectRatio)
                .format(format)
                .build();
    }

    private static JigsawInfo buildByRowsAndFormat(final JigsawInfo input) {
        final int rows = input.getRows().orElse(0);
        final String format = input.getFormat().orElse("");
        if (!format.equals(FORMAT_SQUARE)) {
            throw new IllegalArgumentException("Insufficient data");
        }
        final int pieces = rows * rows;
        final double aspectRatio = 1.0;
        final int border = (2 * rows) + (rows - 2) * 2;
        final int inside = pieces - border;

        return new JigsawInfo.Builder()
                .pieces(pieces)
                .border(border)
                .inside(inside)
                .rows(rows)
                .columns(rows)
                .aspectRatio(aspectRatio)
                .format(format)
                .build();
    }

    private static JigsawInfo buildByInsideAndAspectRatio(final JigsawInfo input) {
        final int inside = input.getInside().orElse(0);
        final double aspectRatio = input.getAspectRatio().orElse(0);
        if (aspectRatio != 1.0) {
            throw new IllegalArgumentException("Insufficient data");
        }
        final String format = getFormatFromAspectRatio(aspectRatio);
        final int rows = (int) (Math.sqrt(inside) + 2);
        final int pieces = rows * rows;
        final int border = (2 * rows) + (rows - 2) * 2;

        return new JigsawInfo.Builder()
                .pieces(pieces)
                .border(border)
                .inside(inside)
                .rows(rows)
                .columns(rows)
                .aspectRatio(aspectRatio)
                .format(format)
                .build();
    }

    private static JigsawInfo buildByRowsAndAspectRatio(final JigsawInfo input) {
        final int rows = input.getRows().orElse(0);
        final double aspectRatio = input.getAspectRatio().orElse(0);
        final int columns = (int) Math.ceil(rows * aspectRatio);
        final int pieces = rows * columns;
        final int border = (2 * columns) + (rows - 2) * 2;
        final int inside = pieces - border;
        final String format = getFormatFromAspectRatio(aspectRatio);

        return new JigsawInfo.Builder()
                .pieces(pieces)
                .border(border)
                .inside(inside)
                .rows(rows)
                .columns(columns)
                .aspectRatio(aspectRatio)
                .format(format)
                .build();
    }

    private static JigsawInfo buildByPiecesBorderAndFormat(final JigsawInfo input) {
        final int pieces = input.getPieces().orElse(0);
        final int border = input.getBorder().orElse(0);
        final int inside = pieces - border;
        final String format = input.getFormat().orElse("");

        final int sumRowCol = (pieces - inside + 4) / 2;
        final double discriminant = Math.pow(sumRowCol, 2) - (4 * pieces);
        if (discriminant < 0) {
            throw new IllegalArgumentException("Invalid data");
        }

        final int x1 = (int) ((sumRowCol + Math.sqrt(discriminant)) / 2);
        final int x2 = (int) ((sumRowCol - Math.sqrt(discriminant)) / 2);

        final int rows;
        final int columns;
        if (format.equals(FORMAT_LANDSCAPE)) {
            rows = Math.min(x1, x2);
            columns = Math.max(x1, x2);
        } else {
            rows = Math.max(x1, x2);
            columns = Math.min(x1, x2);
        }

        final double aspectRatio = (double) columns / rows;

        return new JigsawInfo.Builder()
                .pieces(pieces)
                .border(border)
                .inside(inside)
                .rows(rows)
                .columns(columns)
                .aspectRatio(aspectRatio)
                .format(format)
                .build();
    }

    private static JigsawInfo buildByRowsColumnsAndFormat(final JigsawInfo input) {
        final int rows = input.getRows().orElse(0);
        final int columns = input.getColumns().orElse(0);
        final String format = input.getFormat().orElse("");

        if ((format.equals(FORMAT_SQUARE) && rows != columns)
                || (format.equals(FORMAT_LANDSCAPE) && rows > columns)
                || format.equals(FORMAT_PORTRAIT) && rows < columns) {
            throw new IllegalArgumentException("Contradictory data");
        }

        final int pieces = rows * columns;
        final int border = (2 * columns) + (rows - 2) * 2;
        final int inside = pieces - border;
        final double aspectRatio = (double) columns / rows;

        return new JigsawInfo.Builder()
                .pieces(pieces)
                .border(border)
                .inside(inside)
                .rows(rows)
                .columns(columns)
                .aspectRatio(aspectRatio)
                .format(format)
                .build();
    }
}
