import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ChangeCalculator {
    private final List<Integer> coins;

    ChangeCalculator(final List<Integer> currencyCoins) {
        this.coins = currencyCoins;
    }

    List<Integer> computeMostEfficientChange(final int grandTotal) {
        if (grandTotal < 0) {
            throw new IllegalArgumentException("Negative totals are not allowed.");
        }

        final int[] dp = new int[grandTotal + 1];
        Arrays.fill(dp, grandTotal + 1);
        dp[0] = 0;

        final int[] parentCoin = new int[grandTotal + 1];

        for (int i = 1; i <= grandTotal; i++) {
            for (final int coin : coins) {
                if (i >= coin && dp[i - coin] + 1 < dp[i]) {
                    dp[i] = dp[i - coin] + 1;
                    parentCoin[i] = coin;
                }
            }
        }

        if (dp[grandTotal] == grandTotal + 1) {
            throw new IllegalArgumentException(
                    "The total %d cannot be represented in the given currency."
                            .formatted(grandTotal));
        }

        final List<Integer> result = new ArrayList<>();
        int remainder = grandTotal;
        while (remainder > 0) {
            final int coin = parentCoin[remainder];
            result.add(coin);
            remainder -= coin;
        }

        return result;
    }
}
