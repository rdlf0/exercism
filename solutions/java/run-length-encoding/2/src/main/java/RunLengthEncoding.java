import java.util.regex.Pattern;

class RunLengthEncoding {
    private static final Pattern PATTERN_ENCODING = Pattern.compile("(.)\\1+");
    private static final Pattern PATTERN_DECODING = Pattern.compile("(\\d+)(.)");

    String encode(final String data) {
        return PATTERN_ENCODING
                .matcher(data)
                .replaceAll(mr -> "%d%s".formatted(mr.group().length(), mr.group(1)));
    }

    String decode(final String data) {
        return PATTERN_DECODING
                .matcher(data)
                .replaceAll(mr -> mr.group(2).repeat(Integer.parseInt(mr.group(1))));
    }
}
