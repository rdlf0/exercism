class RotationalCipher {
    private final int shiftKey;

    RotationalCipher(final int shiftKey) {
        this.shiftKey = shiftKey;
    }

    String rotate(final String data) {
        return data.chars()
                .mapToObj(
                        ch -> {
                            if (!Character.isLetter(ch)) {
                                return (char) ch;
                            }

                            final char base = Character.isLowerCase(ch) ? 'a' : 'A';
                            return (char) ((ch - base + shiftKey % 26 + 26) % 26 + base);
                        })
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
