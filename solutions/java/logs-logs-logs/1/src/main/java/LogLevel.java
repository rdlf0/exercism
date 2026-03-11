import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum LogLevel {
    UNKNOWN("UNKNOWN", 0),
    TRACE("TRC", 1),
    DEBUG("DBG", 2),
    INFO("INF", 4),
    WARNING("WRN", 5),
    ERROR("ERR", 6),
    FATAL("FTL", 42),
    ;

    public static final Map<String, LogLevel> LOG_LEVELS_BY_SHORT_CODE =
            Arrays.stream(LogLevel.values())
                    .collect(Collectors.toMap(LogLevel::getShortCode, Function.identity()));

    private final String shortCode;
    private final int encodedLevel;

    LogLevel(final String shortCode, final int encodedLevel) {
        this.shortCode = shortCode;
        this.encodedLevel = encodedLevel;
    }

    public String getShortCode() {
        return shortCode;
    }

    public int getEncodedLevel() {
        return encodedLevel;
    }
}
