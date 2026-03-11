import java.util.Arrays;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

class Poker {

    private final List<Hand> hands;

    Poker(final List<String> hands) {
        this.hands = hands.stream()
            .map(Hand::new)
            .collect(Collectors.toList());
    }

    List<String> getBestHands() {
        final NavigableMap<Integer, List<Hand>> handsByScore = hands.stream()
            .collect(
                Collectors.groupingBy(
                    Hand::getScore,
                    TreeMap::new,
                    Collectors.toList()));

        List<Hand> bestHands = handsByScore.lastEntry().getValue();
        if (bestHands.size() > 1) {
            bestHands = breakTie(bestHands);
        }

        return bestHands.stream()
            .map(Hand::toString)
            .collect(Collectors.toList());
    }

    /**
     * This should take in consideration if there
     * are more than 2 hands with equal scores
     */
    private List<Hand> breakTie(final List<Hand> hands) {
        final int diff = hands.get(0).compareTo(hands.get(1));
        if (diff > 0) {
            return Arrays.asList(hands.get(1));
        } else if (diff < 0) {
            return Arrays.asList(hands.get(0));
        }

        return hands;
    }

}
