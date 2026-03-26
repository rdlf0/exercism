import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class GoCounting {
    private static final int[][] DIRECTIONS = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private final char[][] board;
    private final Set<Set<Point>> territories = new HashSet<>();
    private final Map<Player, Set<Point>> territoriesByOwner =
            Map.of(
                    Player.NONE, new HashSet<>(),
                    Player.BLACK, new HashSet<>(),
                    Player.WHITE, new HashSet<>());

    GoCounting(final String board) {
        final String[] rows = board.split("\n");
        this.board = new char[rows.length][];
        for (int row = 0; row < rows.length; row++) {
            this.board[row] = new char[rows[row].length()];
            for (int col = 0; col < rows[row].length(); col++) {
                this.board[row][col] = rows[row].charAt(col);
            }
        }
    }

    private static Set<Point> traverseTerritory(final char[][] board, final int x, final int y) {
        final Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(x, y));

        final Set<Point> points = new HashSet<>();
        while (!queue.isEmpty()) {
            final int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                final Point current = queue.poll();
                if (current == null) {
                    continue;
                }
                board[current.y][current.x] = '*';
                points.add(current);

                for (final int[] dir : DIRECTIONS) {
                    final int newX = current.x + dir[0];
                    final int newY = current.y + dir[1];

                    if (newY < 0
                            || newY == board.length
                            || newX < 0
                            || newX == board[0].length
                            || board[newY][newX] != ' ') {
                        continue;
                    }

                    queue.offer(new Point(newX, newY));
                }
            }
        }

        return points;
    }

    Player getTerritoryOwner(final int x, final int y) {
        this.validateCoordinate(x, y);

        if (board[y][x] != ' ') {
            return Player.NONE;
        }

        final Set<Point> territory = this.getTerritory(x, y);
        Player owner = Player.NONE;
        for (final Point point : territory) {
            for (final int[] dir : DIRECTIONS) {
                final int newX = point.x + dir[0];
                final int newY = point.y + dir[1];

                if (newY < 0
                        || newY == board.length
                        || newX < 0
                        || newX == board[0].length
                        || !Player.PLAYERS_BY_SYMBOL.containsKey(board[newY][newX])) {
                    continue;
                }

                final Player candidate = Player.PLAYERS_BY_SYMBOL.get(board[newY][newX]);
                if (owner == Player.NONE) {
                    owner = candidate;
                } else if (owner != candidate) {
                    return Player.NONE;
                }
            }
        }
        return owner;
    }

    Set<Point> getTerritory(final int x, final int y) {
        this.validateCoordinate(x, y);

        if (board[y][x] != ' ') {
            return Collections.emptySet();
        }

        // Cache
        for (final Set<Point> territory : territories) {
            if (territory.contains(new Point(x, y))) {
                return territory;
            }
        }

        final char[][] boardCopy = copyBoard();
        final Set<Point> territory = traverseTerritory(boardCopy, x, y);
        territories.add(territory);
        return territory;
    }

    Map<Player, Set<Point>> getTerritories() {
        final char[][] boardCopy = copyBoard();
        for (int y = 0; y < boardCopy.length; y++) {
            for (int x = 0; x < boardCopy[y].length; x++) {
                if (boardCopy[y][x] != ' ') {
                    continue;
                }
                final Set<Point> territory = getTerritory(x, y);
                final Player owner = getTerritoryOwner(x, y);
                territoriesByOwner.get(owner).addAll(territory);
                territory.forEach(point -> boardCopy[point.y][point.x] = '*');
            }
        }

        return territoriesByOwner;
    }

    private void validateCoordinate(final int x, final int y) {
        if (y < 0 || y >= board.length || x < 0 || x >= board[0].length) {
            throw new IllegalArgumentException("Invalid coordinate");
        }
    }

    private char[][] copyBoard() {
        final char[][] copy = new char[board.length][];
        for (int i = 0; i < board.length; i++) {
            copy[i] = Arrays.copyOf(board[i], board[i].length);
        }

        return copy;
    }
}
