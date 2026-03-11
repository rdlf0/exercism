import java.util.Arrays;

class Yacht {

    private final int[] dice;
    private final YachtCategory category;

    Yacht(final int[] dice, final YachtCategory category) {
        Arrays.sort(dice);
        this.dice = dice;
        this.category = category;
    }

    int score() {
        switch (category) {
            case ONES:
            case TWOS:
            case THREES:
            case FOURS:
            case FIVES:
            case SIXES:
                return Arrays.stream(dice).filter(d -> d == category.getPower()).sum();
            case CHOICE:
                return Arrays.stream(dice).sum();
            case LITTLE_STRAIGHT:
                return Arrays.equals(dice, new int[] {1, 2, 3, 4, 5}) ? 30 : 0;
            case BIG_STRAIGHT:
                return Arrays.equals(dice, new int[] {2, 3, 4, 5, 6}) ? 30 : 0;
            case YACHT:
                return dice[0] == dice[dice.length - 1] ? 50 : 0;
            case FULL_HOUSE:
                if ((getOccurrences(dice[0]) == 3 && getOccurrences(dice[dice.length - 1]) == 2)
                        || (getOccurrences(dice[0]) == 2 && getOccurrences(dice[dice.length - 1]) == 3)) {
                    return Arrays.stream(dice).sum();
                }

                return 0;
            case FOUR_OF_A_KIND:
                if (getOccurrences(dice[0]) >= 4) {
                    return dice[0] * 4;
                } else if (getOccurrences(dice[dice.length - 1]) >= 4) {
                    return dice[dice.length - 1] * 4;
                }
                
                return 0;
            default:
                return 0;
        }
    }

    private long getOccurrences(final int number) {
        return Arrays.stream(dice)
                .filter(d -> d == number)
                .count();
    }

}
