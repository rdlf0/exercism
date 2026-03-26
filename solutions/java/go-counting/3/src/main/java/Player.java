import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

enum Player {
    NONE('N'),
    BLACK('B'),
    WHITE('W'),
    ;

    public static final Map<Character, Player> PLAYERS_BY_SYMBOL =
            Arrays.stream(Player.values())
                    .collect(Collectors.toMap(Player::getSymbol, Function.identity()));

    private final char symbol;

    Player(final char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
