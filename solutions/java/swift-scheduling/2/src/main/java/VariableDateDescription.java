import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

enum VariableDateDescription {
    NTH_MONTH(
            Map.of(
                    VariableDateDescription::meetingStartsBeforeNthMonth,
                    VariableDateDescription::deliveryFirstWorkdayOfThisYearsNthMontAt8,
                    VariableDateDescription::meetingDoesNotStartBeforeNthMonth,
                    VariableDateDescription::deliveryFirstWorkdayOfNextYearsNthMontAt8)),
    NTH_QUARTER(
            Map.of(
                    VariableDateDescription::meetingDoesNotStartAfterNthQuarter,
                    VariableDateDescription::deliveryLastWorkdayOfThisYearsNthQuarterAt8,
                    VariableDateDescription::meetingStartsAfterNthQuarter,
                    VariableDateDescription::deliveryLastWorkdayOfNextYearsNthQuarterAt8)),
    ;

    private static final TemporalAdjuster FIRST_WORKDAY_OF_MONTH =
            temporal -> {
                final LocalDateTime date =
                        LocalDateTime.from(temporal).with(TemporalAdjusters.firstDayOfMonth());
                return switch (date.getDayOfWeek()) {
                    case DayOfWeek.SATURDAY -> date.plusDays(2);
                    case DayOfWeek.SUNDAY -> date.plusDays(1);
                    default -> date;
                };
            };
    private static final TemporalAdjuster LAST_WORKDAY_OF_MONTH =
            temporal -> {
                final LocalDateTime date =
                        LocalDateTime.from(temporal).with(TemporalAdjusters.lastDayOfMonth());
                return switch (date.getDayOfWeek()) {
                    case DayOfWeek.SATURDAY -> date.minusDays(1);
                    case DayOfWeek.SUNDAY -> date.minusDays(2);
                    default -> date;
                };
            };

    private final Map<
                    BiPredicate<LocalDateTime, Integer>,
                    BiFunction<LocalDateTime, Integer, LocalDateTime>>
            adjustersByPredicate;

    VariableDateDescription(
            final Map<
                            BiPredicate<LocalDateTime, Integer>,
                            BiFunction<LocalDateTime, Integer, LocalDateTime>>
                    adjustersByPredicate) {
        this.adjustersByPredicate = adjustersByPredicate;
    }

    private static boolean meetingStartsBeforeNthMonth(
            final LocalDateTime meetingStart, final int month) {
        return meetingStart.isBefore(meetingStart.withMonth(month));
    }

    private static boolean meetingDoesNotStartBeforeNthMonth(
            final LocalDateTime meetingStart, final int month) {
        return !meetingStart.isBefore(meetingStart.withMonth(month));
    }

    private static boolean meetingDoesNotStartAfterNthQuarter(
            final LocalDateTime meetingStart, final int quarter) {
        return (meetingStart.getMonthValue() - 1) / 3 + 1 <= quarter;
    }

    private static boolean meetingStartsAfterNthQuarter(
            final LocalDateTime meetingStart, final int quarter) {
        return (meetingStart.getMonthValue() - 1) / 3 + 1 > quarter;
    }

    private static LocalDateTime deliveryFirstWorkdayOfThisYearsNthMontAt8(
            final LocalDateTime meetingStart, final int month) {
        return meetingStart
                .withHour(8)
                .withMinute(0)
                .withMonth(month)
                .withYear(meetingStart.getYear())
                .with(FIRST_WORKDAY_OF_MONTH);
    }

    private static LocalDateTime deliveryFirstWorkdayOfNextYearsNthMontAt8(
            final LocalDateTime meetingStart, final int month) {
        return meetingStart
                .withHour(8)
                .withMinute(0)
                .withMonth(month)
                .withYear(meetingStart.getYear() + 1)
                .with(FIRST_WORKDAY_OF_MONTH);
    }

    private static LocalDateTime deliveryLastWorkdayOfThisYearsNthQuarterAt8(
            final LocalDateTime meetingStart, final int quarter) {
        return meetingStart
                .withHour(8)
                .withMinute(0)
                .withMonth(quarter * 3)
                .withYear(meetingStart.getYear())
                .with(LAST_WORKDAY_OF_MONTH);
    }

    private static LocalDateTime deliveryLastWorkdayOfNextYearsNthQuarterAt8(
            final LocalDateTime meetingStart, final int quarter) {
        return meetingStart
                .withHour(8)
                .withMinute(0)
                .withMonth(quarter * 3)
                .withYear(meetingStart.getYear() + 1)
                .with(LAST_WORKDAY_OF_MONTH);
    }

    public Map<
                    BiPredicate<LocalDateTime, Integer>,
                    BiFunction<LocalDateTime, Integer, LocalDateTime>>
            getAdjustersByPredicate() {
        return adjustersByPredicate;
    }
}
