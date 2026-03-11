import java.util.Arrays;
import java.util.stream.Collectors;

class Acronym {

    private String phrase;

    Acronym(String phrase) {
        this.phrase = phrase;
    }

    String get() {
        return Arrays.stream(this.phrase.split("[^a-zA-Z']"))
                .filter(w -> !w.isEmpty())
                .map(w -> String.valueOf(w.charAt(0)))
                .collect(Collectors.joining());
    }

}
