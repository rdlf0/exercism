class Proverb {

    private static final String PROVERB_LINE = "For want of a %s the %s was lost.\n";
    private static final String PROVERB_LAST_LINE = "And all for the want of a %s.";

    private String[] words;

    Proverb(String[] words) {
        this.words = words;
    }

    String recite() {
        StringBuilder sb = new StringBuilder();
        String lastLine = "";

        for (int i = 0; i < this.words.length; i++) {
            if (i == 0) {
                lastLine = String.format(PROVERB_LAST_LINE, this.words[i]);
            }

            if (i + 1 < this.words.length) {
                sb.append(String.format(PROVERB_LINE, this.words[i], this.words[i+1]));
            }
        }

        return sb.append(lastLine).toString();
    }

}
