import java.util.List;

class Knapsack {

    int maximumValue(final int capacity, final List<Item> items) {
        int[][] memo = new int[items.size() + 1][capacity + 1];

        for (int i = 1; i <= items.size(); i++) {
            for (int j = 0; j <= capacity; j++) {
                if (items.get(i - 1).getWeight() > j) {
                    memo[i][j] = memo[i - 1][j];
                } else {
                    memo[i][j] = Math.max(
                            memo[i - 1][j],
                            memo[i - 1][j - items.get(i - 1).getWeight()]
                                    + items.get(i - 1).getValue());
                }
            }
        }

        return memo[items.size()][capacity];
    }

}
