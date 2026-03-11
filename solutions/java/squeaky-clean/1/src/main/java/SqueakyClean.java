import java.util.Map;
import java.util.regex.Pattern;

final class SqueakyClean {
    private static final Pattern KEBABP_CASE_PATTERN = Pattern.compile("-([a-zA-Z])");
    private static final Map<Character, Character> REPLACEMENTS =
            Map.of(
                    ' ', '_',
                    '1', 'l',
                    '3', 'e',
                    '4', 'a',
                    '7', 't',
                    '0', 'o');

    private SqueakyClean() {}

    static String clean(final String identifier) {
        if (identifier == null || identifier.isEmpty()) {
            return "";
        }

        final String replaced =
                identifier
                        .chars()
                        .mapToObj(c -> (char) c)
                        .map(c -> REPLACEMENTS.getOrDefault(c, c))
                        .filter(ch -> Character.isLetter(ch) || ch == '_' || ch == '-')
                        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                        .toString();

        return KEBABP_CASE_PATTERN.matcher(replaced).replaceAll(mr -> mr.group(1).toUpperCase());
    }
}
