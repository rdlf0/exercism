import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class LogLevels {
    private static final Pattern PATTERN_LEVEL = Pattern.compile("^[|]$");

    public static String message(String logLine) {
        final List<String> lineParts = Arrays.stream(logLine.split(":")).toList();
        return lineParts.get(1).strip();
    }

    public static String logLevel(String logLine) {
        final List<String> lineParts = Arrays.stream(logLine.split(":")).toList();
        return PATTERN_LEVEL.matcher(lineParts.getFirst()).replaceAll("").toLowerCase().trim();
    }

    public static String reformat(String logLine) {
        return String.format("%s (%s)", message(logLine), logLevel(logLine));
    }
}
