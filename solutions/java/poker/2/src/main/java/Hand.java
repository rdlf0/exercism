import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
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

        if (isMultipleOccurrences(4, 1)) {
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

        if (isMultipleOccurrences(3, 1)) {
            return 300;
        }

        if (isMultipleOccurrences(2, 2)) {
            return 200;
        }

        if (isMultipleOccurrences(2, 1)) {
            return 100;
        }

        return cards.peek().getRank();
    }

    private boolean isStraightFlush() {
        return (isStraight() || isStraightToA()) && isFlush();
    }

    private boolean isFullHause() {
        return isMultipleOccurrences(3, 1) && isMultipleOccurrences(2, 1);
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

    private boolean isMultipleOccurrences(final int groupSize, final int occurrences) {
        return cards.stream()
            .collect(Collectors.groupingBy(Card::getRank))
            .entrySet()
            .stream()
            .filter(e -> e.getValue().size() == groupSize)
            .count() == occurrences;
    }

    public NavigableMap<Integer, List<Card>> getCardsBySizeOfRankGroup() {
        final Map<Integer, List<Card>> byRank = cards.stream()
            .collect(Collectors.groupingBy(Card::getRank));

        return byRank.values().stream()
            .collect(
                Collectors.groupingBy(
                    List::size,
                    TreeMap::new,
                    Collectors.flatMapping(
                        List::stream,
                        Collectors.toList())));
    }

    @Override
    public int compareTo(final Hand other) {
        final int initialDiff = other.getScore() - this.getScore();

        if (initialDiff != 0) {
            return initialDiff;
        }

        final NavigableMap<Integer, List<Card>> thisBySizeOfRankGroup =
            this.getCardsBySizeOfRankGroup();
        final NavigableMap<Integer, List<Card>> otherBySizeOfRankGroup =
            other.getCardsBySizeOfRankGroup();

        int diff = 0;
        for (final Integer key : thisBySizeOfRankGroup.descendingKeySet()) {
            final List<Card> cardsInGroup = thisBySizeOfRankGroup.get(key);
            final List<Card> otherCardsInGroup = otherBySizeOfRankGroup.get(key);
            Collections.sort(cardsInGroup);
            Collections.sort(otherCardsInGroup);

            for (int i = 0; i < cardsInGroup.size(); i += key) {
                diff = cardsInGroup.get(i).compareTo(otherCardsInGroup.get(i));
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
