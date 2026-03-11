import java.util.Arrays;
import java.util.stream.Collectors;

class Acronym {

    private String phrase;

    Acronym(String phrase) {
        this.phrase = phrase;
    }

    String get() {
        String clearPhrase = this.phrase
                .toUpperCase()
                .replaceAll("['_]", "");

        return Arrays.stream(clearPhrase.split("(\\W+)"))
                .map(w -> String.valueOf(w.charAt(0)))
                .collect(Collectors.joining());
    }

}
