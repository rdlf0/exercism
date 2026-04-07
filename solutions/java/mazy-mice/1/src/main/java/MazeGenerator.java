import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MazeGenerator {
    private static final int SIDE_MIN = 5;
    private static final int SIDE_MAX = 100;
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    private static final Random RANDOM = new Random();
    private static final char CHAR_ALL_WALLS = '┼';
    private static final char CHAR_EMPTY_SPACE = ' ';
    private static final char CHAR_ARROW = '⇨';

    private static void generatePath(
            final int row, final int col, final char[][] maze, final boolean[][] visited) {
        visited[row][col] = true;
        Collections.shuffle(Arrays.asList(DIRECTIONS), RANDOM);

        for (final int[] dir : DIRECTIONS) {
            final int newRow = row + dir[0];
            final int newCol = col + dir[1];

            if (newRow < 0
                    || newRow >= visited.length
                    || newCol < 0
                    || newCol >= visited[0].length
                    || visited[newRow][newCol]) {
                continue;
            }

            if (maze[2 * row + 1 + dir[0]][2 * col + 1 + dir[1]] == CHAR_ALL_WALLS) {
                maze[2 * row + 1 + dir[0]][2 * col + 1 + dir[1]] = CHAR_EMPTY_SPACE;
            }

            generatePath(newRow, newCol, maze, visited);
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

        final int width = 2 * columns + 1;
        final int height = 2 * rows + 1;
        final char[][] maze = new char[height][width];
        final boolean[][] visited = new boolean[rows][columns];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = CHAR_ALL_WALLS;
            }
        }

        generatePath(0, 0, maze, visited);

        maze[1][0] = CHAR_ARROW;
        maze[height - 2][width - 1] = CHAR_ARROW;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (visited[row][col]) {
                    maze[2 * row + 1][2 * col + 1] = CHAR_EMPTY_SPACE;
                }
            }
        }

        return maze;
    }
}
