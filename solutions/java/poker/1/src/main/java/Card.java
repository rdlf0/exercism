import java.util.Map;

class Card implements Comparable<Card> {

    private final int rank;
    private final char suit;

    private static final Map<String, Integer> NON_NUMERIC_RANKS = Map.ofEntries(
        Map.entry("J", 11),
        Map.entry("Q", 12),
        Map.entry("K", 13),
        Map.entry("A", 14));

    public Card(final String cardString) {
        final String rankString = cardString.substring(0, cardString.length() - 1);
        if (NON_NUMERIC_RANKS.containsKey(rankString)) {
            this.rank = NON_NUMERIC_RANKS.get(rankString);
        } else {
            this.rank = Integer.parseInt(rankString);
        }
        this.suit = cardString.charAt(cardString.length() - 1);
    }

    public int getRank() {
        return rank;
    }

    public char getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Card other) {
        return other.getRank() - this.getRank();
    }

}
