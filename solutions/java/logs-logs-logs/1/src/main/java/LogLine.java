import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogLine {
    private static final Pattern SHORT_CODE_PATTERN = Pattern.compile("^\\[(\\w{3})\\]: ");

    private final Matcher matcher;

    public LogLine(final String logLine) {
        this.matcher = SHORT_CODE_PATTERN.matcher(logLine);
    }

    public LogLevel getLogLevel() {
        if (matcher.find()) {
            return LogLevel.LOG_LEVELS_BY_SHORT_CODE.getOrDefault(
                    matcher.group(1), LogLevel.UNKNOWN);
        }

        // Unreachable, according to the problem description, but just in case...
        return LogLevel.UNKNOWN;
    }

    public String getOutputForShortLog() {
        // The problem description guarantees that log level is present in every log line
        return matcher.replaceFirst("%d:".formatted(this.getLogLevel().getEncodedLevel()));
    }
}
