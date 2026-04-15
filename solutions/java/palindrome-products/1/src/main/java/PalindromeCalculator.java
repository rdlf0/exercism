import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BiPredicate;

/** For some reason tests instantiate this class, but use it statically 🤷 */
@SuppressWarnings("UtilityClassWithoutPrivateConstructor")
final class PalindromeCalculator {
    static SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(
            final int minFactor, final int maxFactor) {
        if (minFactor > maxFactor) {
            throw new IllegalArgumentException("invalid input: min must be <= max");
        }

        long minProduct = Long.MAX_VALUE;
        final Set<List<Integer>> minProductFactors = new HashSet<>();
        for (int i = minFactor; i <= maxFactor; i++) {
            for (int j = i; j <= maxFactor; j++) {
                minProduct = getTargetProduct(i, j, minProduct, minProductFactors, (a, b) -> a < b);
            }
        }

        long maxProduct = 0;
        final Set<List<Integer>> maxProductFactors = new HashSet<>();
        for (int j = maxFactor; j >= minFactor; j--) {
            for (int i = j; i >= minFactor; i--) {
                maxProduct = getTargetProduct(i, j, maxProduct, maxProductFactors, (a, b) -> a > b);
            }
        }

        final SortedMap<Long, List<List<Integer>>> result = new TreeMap<>();
        if (!minProductFactors.isEmpty()) {
            result.put(minProduct, new ArrayList<>(minProductFactors));
        }
        if (!maxProductFactors.isEmpty()) {
            result.put(maxProduct, new ArrayList<>(maxProductFactors));
        }

        return result;
    }

    private static long getTargetProduct(
            final int i,
            final int j,
            final long targetProduct,
            final Set<List<Integer>> productFactors,
            final BiPredicate<Long, Long> predicate) {
        final long product = (long) i * j;
        if (predicate.test(product, targetProduct) && isPalindrome(product)) {
            productFactors.clear();
            productFactors.add(List.of(i, j));
            return product;
        }

        if (product == targetProduct) {
            productFactors.add(List.of(i, j));
            return product;
        }

        return targetProduct;
    }

    private static boolean isPalindrome(final long num) {
        if (num < 10) {
            return true;
        }

        long rev = 0;
        long x = num;
        while (x != 0) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }

        return rev == num;
    }
}
