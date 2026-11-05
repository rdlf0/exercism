import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

class SalonCalendar {

    private static final int PAYDAY_DAY_OF_MONTH = 15;

    private static LocalDate firstWorkdayBeforeOrSame(final LocalDate date) {
        return switch (date.getDayOfWeek()) {
            case DayOfWeek.SATURDAY -> date.minusDays(1);
            case DayOfWeek.SUNDAY -> date.minusDays(2);
            default -> date;
        };
    }

    public LocalDate standingAppointment(
            final int year, final Month month, final int occurrence, final DayOfWeek dayOfWeek) {
        return LocalDate.of(year, month, 1)
                .with(TemporalAdjusters.dayOfWeekInMonth(occurrence, dayOfWeek));
    }

    public LocalDate lastAppointment(final int year, final Month month, final DayOfWeek dayOfWeek) {
        return LocalDate.of(year, month, 1).with(TemporalAdjusters.lastInMonth(dayOfWeek));
    }

    public LocalDate nextAvailable(final LocalDate from, final DayOfWeek dayOfWeek) {
        return from.with(TemporalAdjusters.nextOrSame(dayOfWeek));
    }

    public LocalDate bookkeepingDay(final int year, final Month month) {
        final LocalDate lastDayOfMonth =
                LocalDate.of(year, month, 1).with(TemporalAdjusters.lastDayOfMonth());
        return firstWorkdayBeforeOrSame(lastDayOfMonth);
    }

    public LocalDate payday(final int year, final Month month) {
        return firstWorkdayBeforeOrSame(LocalDate.of(year, month, PAYDAY_DAY_OF_MONTH));
    }

    public TemporalAdjuster precedingWorkday() {
        return temporal -> firstWorkdayBeforeOrSame(LocalDate.from(temporal));
    }
}
