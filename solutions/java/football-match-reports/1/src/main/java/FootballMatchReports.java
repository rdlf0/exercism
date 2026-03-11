import java.util.HashMap;
import java.util.Map;

public final class FootballMatchReports {
    private static final Map<Integer, String> POSITIONS_BY_NUMBER = new HashMap<>();
    private static final String INVALID_POSITION = "invalid";

    static {
        POSITIONS_BY_NUMBER.put(1, "goalie");
        POSITIONS_BY_NUMBER.put(2, "left back");
        POSITIONS_BY_NUMBER.put(3, "center back");
        POSITIONS_BY_NUMBER.put(4, "center back");
        POSITIONS_BY_NUMBER.put(5, "right back");
        POSITIONS_BY_NUMBER.put(6, "midfielder");
        POSITIONS_BY_NUMBER.put(7, "midfielder");
        POSITIONS_BY_NUMBER.put(8, "midfielder");
        POSITIONS_BY_NUMBER.put(9, "left wing");
        POSITIONS_BY_NUMBER.put(10, "striker");
        POSITIONS_BY_NUMBER.put(11, "right wing");
    }

    private FootballMatchReports() {}

    public static String onField(final int shirtNum) {
        return POSITIONS_BY_NUMBER.getOrDefault(shirtNum, INVALID_POSITION);
    }
}
