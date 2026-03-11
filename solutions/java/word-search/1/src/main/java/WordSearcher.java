import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

class WordSearcher {

    Map<String, Optional<WordLocation>> search(final Set<String> words, final char[][] grid) {
        final Map<String, Optional<WordLocation>> locations = new HashMap<>();

        for (final String word : words) {
            Optional<WordLocation> location = findWord(word, grid);
            location = location.map(WordLocation::toOneBasedIndex);
            locations.put(word, location);
        }

        return locations;
    }

    private Optional<WordLocation> findWord(final String word, final char[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (word.charAt(0) == grid[row][col]) {
                    final Pair start = new Pair(col, row);
                    final Optional<WordLocation> location = findWordByStart(word, start, grid);
                    if (location.isPresent()) {
                        return location;
                    }
                }
            }
        }

        return Optional.empty();
    }

    private Optional<WordLocation> findWordByStart(
        final String word,
        final Pair start,
        final char[][] grid) {
        return findWordByStartAndDirection(word, start, Pair::moveNorth, grid)
            .or(() -> findWordByStartAndDirection(word, start, Pair::moveNorthEast, grid))
            .or(() -> findWordByStartAndDirection(word, start, Pair::moveEast, grid))
            .or(() -> findWordByStartAndDirection(word, start, Pair::moveSouthEast, grid))
            .or(() -> findWordByStartAndDirection(word, start, Pair::moveSouth, grid))
            .or(() -> findWordByStartAndDirection(word, start, Pair::moveSouthWest, grid))
            .or(() -> findWordByStartAndDirection(word, start, Pair::moveWest, grid))
            .or(() -> findWordByStartAndDirection(word, start, Pair::moveNorthWest, grid));
    }

    private Optional<WordLocation> findWordByStartAndDirection(
        final String word,
        final Pair start,
        final UnaryOperator<Pair> direction,
        final char[][] grid) {
        Pair current = new Pair(start.getX(), start.getY());
        for (int i = 1; i < word.length(); i++) {
            current = direction.apply(current);
            final int x = current.getX();
            final int y = current.getY();
            if (x < 0 || x > grid[0].length - 1 ||
                y < 0 || y > grid.length - 1 ||
                word.charAt(i) != grid[y][x]) {
                return Optional.empty();
            }
        }

        return Optional.of(new WordLocation(start, current));
    }
}
