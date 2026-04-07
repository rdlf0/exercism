import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MazeGenerator {
    private static final int SIDE_MIN = 5;
    private static final int SIDE_MAX = 100;
    private static final Random RANDOM = new Random();
    private static final char CHAR_ALL_WALLS = '┼';
    private static final char CHAR_EMPTY = ' ';
    private static final char CHAR_ARROW = '⇨';

    private static void generatePath(final int row, final int col, final char[][] maze) {
        maze[row][col] = CHAR_EMPTY;

        final int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        Collections.shuffle(Arrays.asList(directions), RANDOM);

        for (final int[] dir : directions) {
            final int newRow = row + 2 * dir[0];
            final int newCol = col + 2 * dir[1];

            if (newRow < 0
                    || newRow >= maze.length
                    || newCol < 0
                    || newCol >= maze[0].length
                    || maze[newRow][newCol] == CHAR_EMPTY) {
                continue;
            }

            maze[row + dir[0]][col + dir[1]] = CHAR_EMPTY;

            generatePath(newRow, newCol, maze);
        }
    }

    public char[][] generatePerfectMaze(final int rows, final int columns) {
        return this.generatePerfectMaze(rows, columns, 0);
    }

    public char[][] generatePerfectMaze(final int rows, final int columns, final int seed) {
        if (rows < SIDE_MIN || columns < SIDE_MIN || rows > SIDE_MAX || columns > SIDE_MAX) {
            throw new IllegalArgumentException(
                    "Rows and columns must be between %d and %d".formatted(SIDE_MIN, SIDE_MAX));
        }

        if (seed > 0) {
            RANDOM.setSeed(seed);
        }

        final char[][] maze = new char[2 * rows + 1][2 * columns + 1];
        for (final char[] row : maze) {
            Arrays.fill(row, CHAR_ALL_WALLS);
        }

        maze[1][0] = CHAR_ARROW;
        maze[2 * rows - 1][2 * columns] = CHAR_ARROW;

        generatePath(1, 1, maze);

        return maze;
    }
}
