import java.math.BigInteger;
import java.util.stream.Stream;

class PrimeCalculator {
    int nth(final int nth) {
        return Stream.iterate(BigInteger.valueOf(2), BigInteger::nextProbablePrime)
                .limit(nth)
                .reduce((_, prime) -> prime)
                .map(BigInteger::intValue)
                .orElseThrow(IllegalArgumentException::new);
    }
}
