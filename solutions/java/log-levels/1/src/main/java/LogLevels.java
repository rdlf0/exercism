import java.util.Arrays;
import java.util.List;

public class LogLevels {

    public static String message(String logLine) {
        final List<String> lineParts = Arrays.stream(logLine.split(":")).toList();
        return lineParts.get(1).strip();
    }

    public static String logLevel(String logLine) {
        final List<String> lineParts = Arrays.stream(logLine.split(":")).toList();
        return lineParts.getFirst().replaceAll("^\\[|]$", "").toLowerCase().trim();
    }

    public static String reformat(String logLine) {
        return String.format("%s (%s)", message(logLine), logLevel(logLine));
    }
}
