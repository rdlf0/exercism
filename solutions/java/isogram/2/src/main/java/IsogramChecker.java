import java.util.HashSet;
import java.util.Set;

class IsogramChecker {

    boolean isIsogram(String phrase) {
        if (phrase.length() == 0) return true;

        phrase = phrase.toUpperCase();

        Set<Character> set = new HashSet<>();
        for (int i = 0; i < phrase.length(); i++) {
            char ch = phrase.charAt(i);

            if (!Character.isAlphabetic(ch)) continue;

            if (set.contains(ch)) return false;

            set.add(ch);
        }

        return true;
    }

}
