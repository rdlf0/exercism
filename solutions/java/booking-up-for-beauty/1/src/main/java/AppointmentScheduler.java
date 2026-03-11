import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class AppointmentScheduler {
    private static final String SCHEDULE_DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";
    private static final String DESCRIPTION_FORMAT =
            "'You have an appointment on' EEEE, MMMM d, yyyy, 'at' h:mm a.";
    private static final int AFTERNOON_BEGIN = 12;
    private static final int AFTERNOON_END = 18;
    private static final int ANNIVERSARY_MONTH = 9;
    private static final int ANNIVERSARY_DAY = 15;

    public LocalDateTime schedule(final String appointmentDateDescription) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SCHEDULE_DATE_FORMAT);
        return LocalDateTime.parse(appointmentDateDescription, formatter);
    }

    public boolean hasPassed(final LocalDateTime appointmentDate) {
        return appointmentDate.isBefore(LocalDateTime.now());
    }

    public boolean isAfternoonAppointment(final LocalDateTime appointmentDate) {
        final int hour = appointmentDate.getHour();
        return hour >= AFTERNOON_BEGIN && hour < AFTERNOON_END;
    }

    public String getDescription(final LocalDateTime appointmentDate) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DESCRIPTION_FORMAT);
        return appointmentDate.format(formatter);
    }

    public LocalDate getAnniversaryDate() {
        return LocalDate.of(LocalDate.now().getYear(), ANNIVERSARY_MONTH, ANNIVERSARY_DAY);
    }
}
