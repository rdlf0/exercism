import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Poker {

    private final List<Hand> hands;

    Poker(final List<String> hands) {
        this.hands = hands.stream()
            .map(Hand::new)
            .collect(Collectors.toList());
    }

    List<String> getBestHands() {
        final Map<Integer, List<Hand>> handsByScore = hands.stream()
            .collect(Collectors.groupingBy(Hand::getScore));

        final int highestScore = Collections.max(handsByScore.keySet());

        List<Hand> bestHands = handsByScore.get(highestScore);
        if (bestHands.size() > 1) {
            bestHands = breakTie(bestHands);
        }

        return bestHands.stream()
            .map(Hand::toString)
            .collect(Collectors.toList());
    }

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
