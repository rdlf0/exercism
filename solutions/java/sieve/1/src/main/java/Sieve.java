import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Sieve {

    private final boolean[] primes;

    Sieve(final int maxPrime) {
        primes = new boolean[maxPrime + 1];
        Arrays.fill(primes, 2, primes.length, true);

        for (int i = 2; i < primes.length; i++) {
            if (primes[i]) {
                // Remove multiples
                for (int j = i; j <= maxPrime / i; j++) {
                    primes[i * j] = false;
                }
            }
        }
    }

    List<Integer> getPrimes() {
        return IntStream.range(2, primes.length)
            .boxed()
            .filter(i -> primes[i])
            .collect(Collectors.toList());
    }
}
