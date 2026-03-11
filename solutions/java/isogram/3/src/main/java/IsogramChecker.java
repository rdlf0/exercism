import java.util.HashSet;
import java.util.Set;

class IsogramChecker {

    boolean isIsogram(String phrase) {
        if (phrase.length() == 0) return true;

        char[] chars = phrase.toUpperCase().toCharArray();
        Set<Character> set = new HashSet<>();

        for (char ch : chars) {
            if (!Character.isAlphabetic(ch)) continue;
            if (set.contains(ch)) return false;

            set.add(ch);
        }

        return true;
    }

}
