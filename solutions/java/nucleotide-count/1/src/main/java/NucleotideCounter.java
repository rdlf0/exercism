import java.util.HashMap;
import java.util.Map;

class NucleotideCounter {

    private String strand;

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
        Map<Character, Integer> map = new HashMap<>();

        map.put('A', (int) this.strand.chars().filter(c -> c == 'A').count());
        map.put('C', (int) this.strand.chars().filter(c -> c == 'C').count());
        map.put('G', (int) this.strand.chars().filter(c -> c == 'G').count());
        map.put('T', (int) this.strand.chars().filter(c -> c == 'T').count());

        return map;
    }

}
