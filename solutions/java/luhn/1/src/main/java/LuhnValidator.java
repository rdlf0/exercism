import java.util.stream.IntStream;

class LuhnValidator {

    private String candidate;

    boolean isValid(String candidate) {
        this.candidate = candidate.replace(" ", "");

        if (this.candidate.length() <= 1) return false;

        return IntStream.range(0, this.candidate.length())
                .map(this::applyRule)
                .sum() % 10 == 0;
    }

    private int applyRule(int position) {
        char ch = this.candidate.charAt(position);
        int value = Character.getNumericValue(ch);

        if (this.candidate.length() % 2 == position % 2) {
            value = (value * 2 - 1) % 9 + 1;
        }

        return value;
    }

}