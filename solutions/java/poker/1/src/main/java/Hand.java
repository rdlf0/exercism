import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Hand implements Comparable<Hand>, Iterable<Card> {

    private final String handString;
    private final LinkedList<Card> cards;

    public Hand(final String handString) {
        this.handString = handString;
        this.cards = Stream.of(handString.split(" "))
            .map(Card::new)
            .sorted()
            .collect(Collectors.toCollection(LinkedList::new));
    }

    int getScore() {
        if (isStraightFlush()) {
            return 800;
        }

        if (isFourOfAKind()) {
            return 700;
        }

        if (isFullHause()) {
            return 600;
        }

        if (isFlush()) {
            return 500;
        }

        if (isStraight()) {
            return 400;
        }

        if (isStraightToA()) {
            return 399;
        }

        if (isThreeOfAKind()) {
            return 300;
        }

        if (isTwoPair()) {
            return 200;
        }

        if (isOnePair()) {
            return 100;
        }

        return getHighCard();
    }

    private boolean isStraightFlush() {
        return (isStraight() || isStraightToA()) && isFlush();
    }

    private boolean isFourOfAKind() {
        return cards.stream()
            .collect(Collectors.groupingBy(Card::getRank))
            .entrySet()
            .stream()
            .filter(e -> e.getValue().size() == 4)
            .count() == 1;
    }

    private boolean isFullHause() {
        return isThreeOfAKind() && isOnePair();
    }

    private boolean isFlush() {
        return cards.stream()
            .collect(Collectors.groupingBy(Card::getSuit))
            .size() == 1;
    }

    private boolean isStraightToA() {
        final List<Integer> ranks = cards.stream()
            .map(Card::getRank)
            .collect(Collectors.toList());

        return ranks.equals(Arrays.asList(14, 5, 4, 3, 2));
    }

    private boolean isStraight() {
        int rank = cards.peek().getRank();

        final Iterator<Card> it = cards.iterator();
        while (it.hasNext()) {
            if (it.next().getRank() != rank--) {
                return false;
            }
        }

        return true;
    }

    private boolean isThreeOfAKind() {
        return cards.stream()
            .collect(Collectors.groupingBy(Card::getRank))
            .entrySet()
            .stream()
            .filter(e -> e.getValue().size() == 3)
            .count() == 1;
    }

    private boolean isTwoPair() {
        return cards.stream()
            .collect(Collectors.groupingBy(Card::getRank))
            .entrySet()
            .stream()
            .filter(e -> e.getValue().size() == 2)
            .count() == 2;
    }

    private boolean isOnePair() {
        return cards.stream()
            .collect(Collectors.groupingBy(Card::getRank))
            .entrySet()
            .stream()
            .filter(e -> e.getValue().size() == 2)
            .count() == 1;
    }

    private int getHighCard() {
        return cards.peek().getRank();
    }

    public TreeMap<Integer, List<Card>> getCardsByRank() {
        return cards.stream().collect(
            Collectors.groupingBy(
                Card::getRank,
                TreeMap::new,
                Collectors.toList()));
    }

    @Override
    public int compareTo(Hand other) {
        final int initialDiff = other.getScore() - this.getScore();

        if (initialDiff != 0) {
            return initialDiff;
        }

        final TreeMap<Integer, List<Card>> byRank = this.getCardsByRank()
            .entrySet()
            .stream()
            .collect(
                Collectors.groupingBy(
                    e -> e.getValue().size(),
                    TreeMap::new,
                    Collectors.flatMapping(
                        e -> e.getValue().stream(),
                        Collectors.toList())));

        final TreeMap<Integer, List<Card>> otherByRank = other.getCardsByRank()
            .entrySet()
            .stream()
            .collect(
                Collectors.groupingBy(
                    e -> e.getValue().size(),
                    TreeMap::new,
                    Collectors.flatMapping(
                        e -> e.getValue().stream(),
                        Collectors.toList())));

        int diff = 0;
        for (Integer key : byRank.descendingKeySet()) {
            final List<Card> cardsInGroup = byRank.get(key);
            final List<Card> otherCardsInGroup = otherByRank.get(key);
            Collections.sort(cardsInGroup);
            Collections.sort(otherCardsInGroup);

            for (int i = 0; i < cardsInGroup.size(); i++) {
                diff = otherCardsInGroup.get(i).getRank() - cardsInGroup.get(i).getRank();
                if (diff != 0) {
                    return diff;
                }
            }
        }

        return initialDiff;
    }

    @Override
    public String toString() {
        return handString;
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

}
