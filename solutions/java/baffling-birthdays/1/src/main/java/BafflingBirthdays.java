import java.time.LocalDate;
import java.time.MonthDay;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class BafflingBirthdays {
    private static final Random SEED = new Random();
    private static final int NON_LEAP_YEAR = 2026;
    private static final double DAYS_IN_YEAR = 365.0;

    boolean sharedBirthday(final List<LocalDate> birthdates) {
        final Set<MonthDay> birthdays =
                birthdates.stream()
                        .map(bd -> MonthDay.of(bd.getMonth(), bd.getDayOfMonth()))
                        .collect(Collectors.toSet());

        return birthdates.size() != birthdays.size();
    }

    List<LocalDate> randomBirthdates(final int groupSize) {
        return Stream.generate(
                        () -> LocalDate.of(NON_LEAP_YEAR, SEED.nextInt(1, 12), SEED.nextInt(1, 29)))
                .limit(groupSize)
                .toList();
    }

    double estimatedProbabilityOfSharedBirthday(final int groupSize) {
        double probability = 1.0;
        for (int i = 0; i < groupSize; i++) {
            probability *= (DAYS_IN_YEAR - i) / DAYS_IN_YEAR;
        }

        return (1.0 - probability) * 100.0;
    }
}
