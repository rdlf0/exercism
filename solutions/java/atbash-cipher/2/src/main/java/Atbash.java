class Atbash {
    private static final int GROUP_SIZE = 5;
    private static final String GROUP_REGEX = "(.{%d})(?!$)".formatted(GROUP_SIZE);

    String encode(final String input) {
        return this.decode(input).replaceAll(GROUP_REGEX, "$1 ");
    }

    String decode(final String input) {
        return input.chars()
                .filter(Character::isLetterOrDigit)
                .mapToObj(
                        ch -> {
                            if (Character.isLetter(ch)) {
                                return (char) ('z' - Character.toLowerCase(ch) + 'a');
                            }
                            return (char) ch;
                        })
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
