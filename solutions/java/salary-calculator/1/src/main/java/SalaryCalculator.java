public class SalaryCalculator {
    private static final int THRESHOLD_DAYS_SKIPPED = 5;
    private static final double MULTIPLIER_BLOW_THRESHOLD = 1.0;
    private static final double MULTIPLIER_ABOVE_THRESHOLD = 0.85;

    private static final int THRESHOLD_BONUS_MULTIPLIER = 20;
    private static final int BONUS_BELOW_THRESHOLD = 10;
    private static final int BONUS_ABOVE_THRESHOLD = 13;

    private static final double BASE_SALARY = 1000.0;
    private static final double MAX_SALARY = 2000.0;

    public double salaryMultiplier(final int daysSkipped) {
        return daysSkipped < THRESHOLD_DAYS_SKIPPED
                ? MULTIPLIER_BLOW_THRESHOLD
                : MULTIPLIER_ABOVE_THRESHOLD;
    }

    public int bonusMultiplier(final int productsSold) {
        return productsSold < THRESHOLD_BONUS_MULTIPLIER
                ? BONUS_BELOW_THRESHOLD
                : BONUS_ABOVE_THRESHOLD;
    }

    public double bonusForProductsSold(final int productsSold) {
        return productsSold * bonusMultiplier(productsSold);
    }

    public double finalSalary(final int daysSkipped, final int productsSold) {
        final double salary =
                BASE_SALARY * salaryMultiplier(daysSkipped) + bonusForProductsSold(productsSold);
        return Math.min(salary, MAX_SALARY);
    }
}
