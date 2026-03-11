import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class Alphametics {

    private static final String EQUALS = " == ";
    private static final String PLUS = " \\+ ";
    private static final List<Integer> VALUES = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    private final String[] left;
    private final String[] right;
    private final Map<Character, Integer> guess;
    private final Set<Character> nonZeroLetters;
    private final List<List<Integer>> variations;

    Alphametics(final String equation) {
        guess = new LinkedHashMap<>();
        nonZeroLetters = new HashSet<>();
        variations = new ArrayList<>();

        final String[] parts = equation.split(EQUALS);
        this.left = parts[0].split(PLUS);
        this.right = new String[] {parts[1]};

        this.findAllCharacters(equation);
        this.findNonZeroCharacters(this.left);
        this.findNonZeroCharacters(this.right);
    }

    Map<Character, Integer> solve() throws UnsolvablePuzzleException {
        this.generateVariations(VALUES, 0, this.guess.size());

        for (final List<Integer> variation : variations) {
            if (this.checkVariation(variation)) {
                return this.guess;
            }
        }

        throw new UnsolvablePuzzleException();
    }

    private boolean checkVariation(final Iterable<Integer> values) {
        final Iterator<Character> keys = this.guess.keySet().iterator();

        for (final Integer value : values) {
            final Character key = keys.next();

            if (value == 0 && this.nonZeroLetters.contains(key)) {
                return false;
            }

            guess.put(key, value);
        }

        return this.evaluate(this.left) == this.evaluate(this.right);
    }

    private void findAllCharacters(final String expression) {
        expression.chars()
            .mapToObj(c -> (char) c)
            .filter(Character::isAlphabetic)
            .forEach(c -> this.guess.put(c, 0));
    }

    private void findNonZeroCharacters(final String[] parts) {
        Arrays.stream(parts).forEach(p -> this.nonZeroLetters.add(p.charAt(0)));
    }

    private int evaluate(final String[] expressions) {
        return Arrays.stream(expressions)
            .map(part -> part.chars()
                .map(c -> this.guess.get((char) c))
                .reduce(0, (a, b) -> 10 * a + b))
            .mapToInt(x -> x)
            .sum();
    }

    private void generateVariations(final List<Integer> set, final int position, final int to) {
        if (position == to) {
            this.variations.add(new ArrayList<>(set.subList(0, to)));
            return;
        }

        for (int i = position; i < set.size(); i++) {
            Collections.swap(set, position, i);
            this.generateVariations(set, position + 1, to);
            Collections.swap(set, position, i);
        }
    }
}
