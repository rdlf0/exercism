import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

enum FixedDateDescription {
    NOW(Map.of(_ -> true, FixedDateDescription::deliveryDateAfter2Hours)),
    ASAP(
            Map.of(
                    FixedDateDescription::meetingStartsBefore13,
                    FixedDateDescription::deliveryDateTodayAt17,
                    Predicate.not(FixedDateDescription::meetingStartsBefore13),
                    FixedDateDescription::deliveryDateTomorrowAt13)),
    EOW(
            Map.of(
                    FixedDateDescription::meetingStartsFirstThreeDaysOfWeek,
                    FixedDateDescription::deliveryDateFridayAt17,
                    FixedDateDescription::meetingStartsThursdayOrFriday,
                    FixedDateDescription::deliveryDateSundayAt20)),
    ;

    public static final Map<String, FixedDateDescription> VALUES_BY_NAME =
            Arrays.stream(FixedDateDescription.values())
                    .collect(Collectors.toMap(FixedDateDescription::name, Function.identity()));

    private final Map<Predicate<LocalDateTime>, UnaryOperator<LocalDateTime>> adjustersByPredicate;

    FixedDateDescription(
            final Map<Predicate<LocalDateTime>, UnaryOperator<LocalDateTime>>
                    adjustersByPredicate) {
        this.adjustersByPredicate = adjustersByPredicate;
    }

    private static boolean meetingStartsBefore13(final LocalDateTime meetingStart) {
        return meetingStart.isBefore(meetingStart.withHour(13));
    }

    private static boolean meetingStartsFirstThreeDaysOfWeek(final LocalDateTime meetingStart) {
        return EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY)
                .contains(meetingStart.getDayOfWeek());
    }

    private static boolean meetingStartsThursdayOrFriday(final LocalDateTime meetingStart) {
        return EnumSet.of(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY)
                .contains(meetingStart.getDayOfWeek());
    }

    private static LocalDateTime deliveryDateAfter2Hours(final LocalDateTime meetingStart) {
        return meetingStart.plusHours(2);
    }

    private static LocalDateTime deliveryDateTodayAt17(final LocalDateTime meetingStart) {
        return meetingStart.withHour(17).withMinute(0);
    }

    private static LocalDateTime deliveryDateTomorrowAt13(final LocalDateTime meetingStart) {
        return meetingStart.plusDays(1).withHour(13).withMinute(0);
    }

    private static LocalDateTime deliveryDateFridayAt17(final LocalDateTime meetingStart) {
        return meetingStart
                .with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
                .withHour(17)
                .withMinute(0);
    }

    private static LocalDateTime deliveryDateSundayAt20(final LocalDateTime meetingStart) {
        return meetingStart
                .with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
                .withHour(20)
                .withMinute(0);
    }

    public Map<Predicate<LocalDateTime>, UnaryOperator<LocalDateTime>> getAdjustersByPredicate() {
        return adjustersByPredicate;
    }
}
