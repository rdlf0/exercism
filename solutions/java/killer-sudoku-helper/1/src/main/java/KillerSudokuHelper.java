import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KillerSudokuHelper {

    List<List<Integer>> combinationsInCage(
            final Integer cageSum, final Integer cageSize, final List<Integer> exclude) {
        if (cageSize == 1) {
            return List.of(List.of(cageSum));
        }

        final List<List<Integer>> result = new ArrayList<>();
        kSum(cageSize, 1, cageSum, exclude, result, new ArrayList<>());
        return result;
    }

    List<List<Integer>> combinationsInCage(final Integer cageSum, final Integer cageSize) {
        return combinationsInCage(cageSum, cageSize, Collections.emptyList());
    }

    private void kSum(
            final int size,
            final int start,
            final int target,
            final List<Integer> exclude,
            final List<List<Integer>> result,
            final List<Integer> group) {
        if (size == 2) {
            int left = start;
            int right = 9;
            while (left < right) {
                final long sum = left + right;
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    result.add(new ArrayList<>(group));
                    result.getLast().add(left);
                    result.getLast().add(right);

                    if (result.getLast().removeAll(exclude)) {
                        result.removeLast();
                    }

                    left++;
                    right--;
                }
            }
            return;
        }

        for (int i = start; i < 11 - size; i++) {
            group.add(i);
            kSum(size - 1, i + 1, target - i, exclude, result, group);
            group.removeLast();
        }
    }
}
