import java.util.*;

public class PigLatinTranslator {

    private Set<String> vowels;
    private List<String> clusters;

    public PigLatinTranslator() {
        this.vowels = new HashSet<>();
        this.vowels.add("a");
        this.vowels.add("e");
        this.vowels.add("i");
        this.vowels.add("o");
        this.vowels.add("u");
        this.vowels.add("xr");
        this.vowels.add("yt");

        this.clusters = new ArrayList<>();
        this.clusters.add("ch");
        this.clusters.add("qu");
        this.clusters.add("rh");
        this.clusters.add("thr");
        this.clusters.add("th");
        this.clusters.add("sch");
        this.clusters.add("sh");
    }

    public String translate(String input) {
        List<String> result = new ArrayList<>();
        for (String word : input.split(" ")) {
            result.add(this.applyRules(word));
        }

        return String.join(" ", result);
    }

    private String applyRules(String input) {
        // Rule 1
        for (String vowel : this.vowels) {
            if (input.startsWith(vowel)) {
                return input + "ay";
            }
        }

        // Rule 2
        for (String cluster : this.clusters) {
            if (input.startsWith(cluster)) {
                return input.substring(cluster.length()) + cluster + "ay";
            }
        }

        // Rule 3
        if (input.matches("^(.{1})qu(.*)")) {
            return input.substring(3) + input.charAt(0) + "quay";
        }

        return input.substring(1) + input.charAt(0) + "ay";
    }

}
