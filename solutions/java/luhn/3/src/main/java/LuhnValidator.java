import java.util.stream.IntStream;

class LuhnValidator {

    private String candidate;

    boolean isValid(String candidate) {
        this.candidate = candidate.replace(" ", "");

        if (this.candidate.length() <= 1) return false;

        return IntStream.range(0, this.candidate.length())
                .map(this::calculateValue)
                .sum() % 10 == 0;
    }

    private int calculateValue(int position) {
        char ch = this.candidate.charAt(position);
        int value = Character.getNumericValue(ch);
        int length = this.candidate.length();

        if (length % 2 == position % 2) {
            value = (value * 2 - 1) % 9 + 1;
        }

        // This can replace the previous 3 lines, but is less readable
        // value = (value * ((length + position) % 2 * -1 + 2) - 1) % 9 + 1;

        return value;
    }

}