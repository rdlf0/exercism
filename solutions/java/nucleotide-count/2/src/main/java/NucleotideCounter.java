import java.util.HashMap;
import java.util.Map;

class NucleotideCounter {

    private String strand;
    private final Map<Character, Integer> map = new HashMap<>(
            Map.ofEntries(
                    Map.entry('A', 0),
                    Map.entry('C', 0),
                    Map.entry('G', 0),
                    Map.entry('T', 0)
            )
    );

    NucleotideCounter(String strand) {
        this.strand = strand;
        this.validate();
    }

    private void validate() {
        if (!this.strand.matches("^[ACGT]*$")) {
            throw new IllegalArgumentException("Invalid strand string!");
        }
    }

    public Map<Character, Integer> nucleotideCounts() {
        this.strand
                .chars()
                .forEach(c -> this.map.merge((char) c, 1, Integer::sum));

        return this.map;
    }

}
