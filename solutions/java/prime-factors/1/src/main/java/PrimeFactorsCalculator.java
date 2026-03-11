import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class PrimeFactorsCalculator {
    List<Long> calculatePrimeFactorsOf(long number) {
        final List<Long> result = new ArrayList<>();
        BigInteger divisor = BigInteger.TWO;
        while (number > 1) {
            final long divisorLong = divisor.longValue();
            if (number % divisorLong != 0) {
                divisor = divisor.nextProbablePrime();
                continue;
            }
            number /= divisorLong;
            result.add(divisorLong);
        }

        return result;
    }
}
