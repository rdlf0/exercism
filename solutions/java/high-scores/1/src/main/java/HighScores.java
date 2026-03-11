import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class HighScores {
    private final List<Integer> highScores;
    private final List<Integer> highScoresSorted;

    public HighScores(final List<Integer> highScores) {
        this.highScores = highScores;
        this.highScoresSorted = new ArrayList<>(highScores);
        this.highScoresSorted.sort(Comparator.reverseOrder());
    }

    List<Integer> scores() {
        return highScores;
    }

    Integer latest() {
        return highScores.getLast();
    }

    Integer personalBest() {
        return highScoresSorted.getFirst();
    }

    List<Integer> personalTopThree() {
        return highScoresSorted.stream().limit(3).toList();
    }
}
