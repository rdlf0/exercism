import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class GrepTool {
    private static LinkedHashMap<String, List<String>> readFiles(final List<String> filenames) {
        return filenames.stream()
                .map(Path::of)
                .collect(
                        Collectors.toMap(
                                Path::toString,
                                path -> {
                                    try {
                                        return Files.readAllLines(path, StandardCharsets.UTF_8);
                                    } catch (final IOException e) {
                                        throw new IllegalArgumentException(
                                                "Cannot read file %s".formatted(path.toString()),
                                                e);
                                    }
                                },
                                (a, _) -> a,
                                LinkedHashMap::new));
    }

    private static Pattern compilePattern(final String pattern, final List<String> flags) {
        final String regex;
        if (flags.contains("-v")) {
            regex = "^((?!%s).)*$".formatted(pattern);
        } else if (flags.contains("-x")) {
            regex = "^%s$".formatted(pattern);
        } else {
            regex = "^.*%s.*$".formatted(pattern);
        }

        if (flags.contains("-i")) {
            return Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        } else {
            return Pattern.compile(regex);
        }
    }

    String grep(final String pattern, final List<String> flags, final List<String> files) {
        final LinkedHashMap<String, List<String>> contentsByFilename = readFiles(files);
        final Pattern regexPattern = compilePattern(pattern, flags);

        final boolean flagL = flags.contains("-l");
        final boolean flagN = flags.contains("-n");

        final List<String> result = new ArrayList<>();
        for (final String filename : contentsByFilename.keySet()) {
            final List<String> lines = contentsByFilename.get(filename);
            for (int i = 0; i < lines.size(); i++) {
                final Matcher matcher = regexPattern.matcher(lines.get(i));
                if (!matcher.matches()) {
                    continue;
                }

                if (flagL) {
                    result.add(filename);
                    break;
                }

                final StringBuilder sb = new StringBuilder();
                if (files.size() > 1) {
                    sb.append(filename).append(":");
                }

                if (flagN) {
                    sb.append(i + 1).append(":");
                }

                sb.append(matcher.group());
                result.add(sb.toString());
            }
        }

        return String.join("\n", result);
    }
}
