import java.util.Map;

class RnaTranscription {

    private static final Map<Character, Character> MAP = Map.ofEntries(
            Map.entry('G', 'C'),
            Map.entry('C', 'G'),
            Map.entry('T', 'A'),
            Map.entry('A', 'U')
    );

    String transcribe(String dnaStrand) {
        return dnaStrand.chars()
                .map(c -> MAP.get((char) c))
                .collect(
                        StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

}
