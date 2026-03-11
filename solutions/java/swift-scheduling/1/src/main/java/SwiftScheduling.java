import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SwiftScheduling {
    private static final Pattern VARIABLE_DATE_PATTERN = Pattern.compile("^(\\d+)M|Q(\\d+)$");

    private SwiftScheduling() {}

    public static LocalDateTime convertToDeliveryDate(
            final LocalDateTime meetingStart, final String description) {
        if (FixedDateDescription.VALUES_BY_NAME.containsKey(description)) {
            return convertFromFixedDateDescription(meetingStart, description);
        } else {
            return convertFromVariableDateDescription(meetingStart, description);
        }
    }

    private static LocalDateTime convertFromFixedDateDescription(
            final LocalDateTime meetingStart, final String description) {
        final Map<Predicate<LocalDateTime>, UnaryOperator<LocalDateTime>> adjustersByPredicate =
                FixedDateDescription.VALUES_BY_NAME.get(description).getAdjustersByPredicate();

        final Optional<UnaryOperator<LocalDateTime>> adjuster =
                adjustersByPredicate.entrySet().stream()
                        .filter(entry -> entry.getKey().test(meetingStart))
                        .findFirst()
                        .map(Map.Entry::getValue);

        return adjuster.map(a -> a.apply(meetingStart))
                .orElseThrow(() -> new IllegalStateException("Invalid meeting start"));
    }

    private static LocalDateTime convertFromVariableDateDescription(
            final LocalDateTime meetingStart, final String description) {
        final Matcher matcher = VARIABLE_DATE_PATTERN.matcher(description);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid description format");
        }

        final int timeFrame;
        final VariableDateDescription variableDescription;
        if (matcher.group(1) != null) {
            timeFrame = Integer.parseInt(matcher.group(1));
            variableDescription = VariableDateDescription.NTH_MONTH;
        } else {
            timeFrame = Integer.parseInt(matcher.group(2));
            variableDescription = VariableDateDescription.NTH_QUARTER;
        }

        final Map<
                        BiPredicate<LocalDateTime, Integer>,
                        BiFunction<LocalDateTime, Integer, LocalDateTime>>
                adjustersByPredicate = variableDescription.getAdjustersByPredicate();

        final Optional<BiFunction<LocalDateTime, Integer, LocalDateTime>> adjuster =
                adjustersByPredicate.entrySet().stream()
                        .filter(entry -> entry.getKey().test(meetingStart, timeFrame))
                        .findFirst()
                        .map(Map.Entry::getValue);

        return adjuster.map(a -> a.apply(meetingStart, timeFrame))
                .orElseThrow(() -> new IllegalStateException("Invalid meeting start"));
    }
}
