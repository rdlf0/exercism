import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class BookStore {
    private static final double REGULAR_PRICE = 8.00;
    private static final double[] GROUP_PRICES = {
        0.0,
        1 * REGULAR_PRICE,
        2 * REGULAR_PRICE * 0.95,
        3 * REGULAR_PRICE * 0.90,
        4 * REGULAR_PRICE * 0.80,
        5 * REGULAR_PRICE * 0.75
    };

    private static double calculateGroupsPrice(
            final List<Integer> counts, final Map<List<Integer>, Double> cache) {
        counts.removeIf(c -> c == 0);
        if (counts.isEmpty()) {
            return 0.0;
        }

        counts.sort(Comparator.reverseOrder());
        if (cache.containsKey(counts)) {
            return cache.get(counts);
        }

        double minPrice = Double.MAX_VALUE;
        for (int groupSize = 1; groupSize <= counts.size(); groupSize++) {
            final List<Integer> remainingCounts = new ArrayList<>(counts);

            for (int j = 0; j < groupSize; j++) {
                remainingCounts.set(j, remainingCounts.get(j) - 1);
            }

            final double groupPrice = GROUP_PRICES[groupSize];
            final double totalPrice = groupPrice + calculateGroupsPrice(remainingCounts, cache);

            minPrice = Math.min(minPrice, totalPrice);
        }

        cache.put(counts, minPrice);

        return minPrice;
    }

    double calculateBasketCost(final List<Integer> books) {
        final Map<Integer, Integer> frequencyMap =
                books.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Function.identity(), Collectors.summingInt(_ -> 1)));

        final Map<List<Integer>, Double> cache = new HashMap<>();

        return calculateGroupsPrice(new ArrayList<>(frequencyMap.values()), cache);
    }
}
