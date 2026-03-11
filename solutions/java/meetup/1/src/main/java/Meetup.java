import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

class Meetup {

    private final LocalDate localDate;

    public Meetup(final int month, final int year) {
        this.localDate = LocalDate.of(year, month, 12);
    }

    LocalDate day(final DayOfWeek dayOfWeek, final MeetupSchedule schedule) {
        switch (schedule) {
            case TEENTH:
                return localDate.with(TemporalAdjusters.next(dayOfWeek));
            case LAST:
                return localDate.with(TemporalAdjusters.lastInMonth(dayOfWeek));
            default:
                return localDate.with(TemporalAdjusters.dayOfWeekInMonth(schedule.ordinal() + 1, dayOfWeek));
        }
    }

}
