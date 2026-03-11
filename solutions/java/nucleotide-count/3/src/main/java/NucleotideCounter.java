import java.util.HashMap;
import java.util.Map;

class NucleotideCounter {

    private String strand;
    private final Map<Character, Integer> map = new HashMap<>(Map.of(
        'A', 0,
        'C', 0,
        'G', 0,
        'T', 0));

    NucleotideCounter(final String strand) {
        this.strand = strand;
        this.validate();
    }

    private void validate() {
        if (!strand.matches("^[ACGT]*$")) {
            throw new IllegalArgumentException("Invalid strand string!");
        }
    }

    Map<Character, Integer> nucleotideCounts() {
        strand
            .chars()
            .forEach(c -> map.merge((char) c, 1, Integer::sum));

        return map;
    }

}
