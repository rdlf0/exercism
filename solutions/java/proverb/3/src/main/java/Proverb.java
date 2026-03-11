class Proverb {

    private static final String PROVERB_LINE = "For want of a %s the %s was lost.\n";
    private static final String PROVERB_LAST_LINE = "And all for the want of a %s.";

    private String[] words;

    Proverb(String[] words) {
        this.words = words;
    }

    String recite() {
        if (this.words.length == 0) return "";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.words.length - 1; i++) {
            sb.append(String.format(PROVERB_LINE, this.words[i], this.words[i + 1]));
        }

        sb.append(String.format(PROVERB_LAST_LINE, this.words[0]));

        return sb.toString();
    }

}
