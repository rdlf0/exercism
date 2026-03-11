enum YachtCategory {

    ONES(1),
    TWOS(2),
    THREES(3),
    FOURS(4),
    FIVES(5),
    SIXES(6),
    CHOICE(7),
    LITTLE_STRAIGHT(8),
    BIG_STRAIGHT(9),
    YACHT(10),
    FULL_HOUSE(11),
    FOUR_OF_A_KIND(12);

    private int power;

    private YachtCategory(final int power) {
        this.power = power;
    }

    int getPower() {
        return power;
    }
}
