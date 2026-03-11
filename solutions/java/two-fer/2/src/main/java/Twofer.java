import java.util.Optional;

class Twofer {

    private static String OUTPUT = "One for %s, one for me.";
    private static String DEFAULT_NAME = "you";

    String twofer(String name) {
        return String.format(OUTPUT, Optional.ofNullable(name).orElse(DEFAULT_NAME));
    }
}
