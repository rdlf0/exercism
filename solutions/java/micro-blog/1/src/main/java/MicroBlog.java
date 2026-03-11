import java.util.stream.Collectors;

class MicroBlog {

    private static final int MAX_LENGTH = 5;

    public String truncate(final String input) {
        return input.codePoints()
            .limit(MAX_LENGTH)
            .mapToObj(Character::toString)
            .collect(Collectors.joining());
    }
}
