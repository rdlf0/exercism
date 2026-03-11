public class PangramChecker {

    public boolean isPangram(String input) {
        input = input.toLowerCase();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (input.indexOf(ch) == -1) return false;
        }

        return true;
    }

}
