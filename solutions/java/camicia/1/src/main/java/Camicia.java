import java.util.List;

public final class Camicia {
    private Camicia() {}

    static CamiciaResult simulateGame(final List<String> playerA, final List<String> playerB) {
        return new Game(playerA, playerB).simulate();
    }
}
