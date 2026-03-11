import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KillerSudokuHelper {

    private static void generateCombinations(
            final int target,
            final int size,
            final int start,
            final List<Integer> exclude,
            final List<Integer> combination,
            final List<List<Integer>> result) {
        if (target == 0 && size == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (i > target || size == 0) {
                break;
            }

            if (exclude.contains(i)) {
                continue;
            }

            combination.add(i);
            generateCombinations(target - i, size - 1, i + 1, exclude, combination, result);
            combination.removeLast();
        }
    }

    List<List<Integer>> combinationsInCage(
            final Integer cageSum, final Integer cageSize, final List<Integer> exclude) {
        final List<List<Integer>> result = new ArrayList<>();
        generateCombinations(cageSum, cageSize, 1, exclude, new ArrayList<>(), result);
        return result;
    }

    List<List<Integer>> combinationsInCage(final Integer cageSum, final Integer cageSize) {
        return combinationsInCage(cageSum, cageSize, Collections.emptyList());
    }
}
