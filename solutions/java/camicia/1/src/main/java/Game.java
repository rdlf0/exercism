import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

final class Game {
    private static final String RESULT_FINISHED = "finished";
    private static final String RESULT_LOOP = "loop";

    private final Deck deckA;
    private final Deck deckB;
    private final Deque<Integer> pile = new ArrayDeque<>();

    private boolean aToMove = true;
    private boolean payingPenalty = false;
    private int penaltyDue = 0;
    private int round = 1;
    private int cardsPlayed = 0;

    Game(final List<String> playerA, final List<String> playerB) {
        this.deckA = new Deck(playerA);
        this.deckB = new Deck(playerB);
    }

    CamiciaResult simulate() {
        final Set<State> seen = new HashSet<>();
        while (true) {
            if (pile.isEmpty() && !seen.add(this.snapshot())) {
                return new CamiciaResult(RESULT_LOOP, cardsPlayed, round - 1);
            }

            final boolean gameOver =
                    payingPenalty && penaltyDue == 0 ? this.collectPile() : this.playTopCard();
            if (gameOver) {
                return new CamiciaResult(RESULT_FINISHED, cardsPlayed, round);
            }
        }
    }

    private boolean playTopCard() {
        if (this.getMover().getCards().isEmpty()) {
            return true;
        }
        final int card = this.getMover().play();
        pile.offer(card);
        cardsPlayed++;

        if (card > 0) {
            penaltyDue = card;
            payingPenalty = true;
            aToMove = !aToMove;
        } else if (penaltyDue > 0) {
            penaltyDue--;
        } else {
            payingPenalty = false;
            aToMove = !aToMove;
        }

        return false;
    }

    private boolean collectPile() {
        this.getWaiter().trick(pile);
        if (this.getMover().getCards().isEmpty()) {
            return true;
        }
        round++;
        payingPenalty = false;
        aToMove = !aToMove;
        return false;
    }

    private Deck getMover() {
        return aToMove ? deckA : deckB;
    }

    private Deck getWaiter() {
        return aToMove ? deckB : deckA;
    }

    private State snapshot() {
        return new State(new Deck(deckA.getCards()), new Deck(deckB.getCards()));
    }
}
