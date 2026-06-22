import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

class Deck {
    private static final Map<String, Integer> PENALTIES_BY_CARD =
            Map.of(
                    "J", 1,
                    "Q", 2,
                    "K", 3,
                    "A", 4);

    private final Queue<Integer> cards = new LinkedList<>();

    public Deck(final List<String> cards) {
        for (final String card : cards) {
            this.cards.offer(PENALTIES_BY_CARD.getOrDefault(card, 0));
        }
    }

    public Deck(final Queue<Integer> cards) {
        this.cards.addAll(cards);
    }

    public Queue<Integer> getCards() {
        return cards;
    }

    public Integer play() {
        return cards.poll();
    }

    public void trick(final Queue<Integer> pile) {
        while (!pile.isEmpty()) {
            cards.offer(pile.poll());
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Deck deck)) return false;
        return Objects.equals(cards, deck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
