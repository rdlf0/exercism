public class PangramChecker {

    public boolean isPangram(String input) {
        long count = input.toLowerCase()
                .chars()
                .filter(c -> c >= 'a' && c <= 'z')
                .distinct()
                .count();

        return count == 26;
    }
}
