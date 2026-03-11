import java.util.regex.Pattern;

public class LogLevels {
    private static final Pattern PATTERN_LEVEL = Pattern.compile("^\\[|]$");

    public static String message(String logLine) {
        return logLine.split(":")[1].strip();
    }

    public static String logLevel(String logLine) {
        return PATTERN_LEVEL.matcher(logLine.split(":")[0]).replaceAll("").toLowerCase().trim();
    }

    public static String reformat(String logLine) {
        return String.format("%s (%s)", message(logLine), logLevel(logLine));
    }
}
