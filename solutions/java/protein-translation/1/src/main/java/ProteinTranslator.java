import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class ProteinTranslator {

    private static final Map<String, String> MAP = Map.ofEntries(
            Map.entry("AUG", "Methionine"),
            Map.entry("UUU", "Phenylalanine"),
            Map.entry("UUC", "Phenylalanine"),
            Map.entry("UUA", "Leucine"),
            Map.entry("UUG", "Leucine"),
            Map.entry("UCU", "Serine"),
            Map.entry("UCC", "Serine"),
            Map.entry("UCA", "Serine"),
            Map.entry("UCG", "Serine"),
            Map.entry("UAU", "Tyrosine"),
            Map.entry("UAC", "Tyrosine"),
            Map.entry("UGU", "Cysteine"),
            Map.entry("UGC", "Cysteine"),
            Map.entry("UGG", "Tryptophan"),
            Map.entry("UAA", "STOP"),
            Map.entry("UAG", "STOP"),
            Map.entry("UGA", "STOP")
    );

    List<String> translate(String rnaSequence) {
        return Arrays.stream(rnaSequence.split("(?<=\\G.{3})"))
                .map(MAP::get)
                .takeWhile(w -> !w.equals("STOP"))
                .collect(Collectors.toList());
    }
}