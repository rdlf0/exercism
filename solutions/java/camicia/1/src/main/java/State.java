import java.util.Objects;

/**
 * An immutable snapshot of both decks, used to detect loops. Two states are equal
 * when the decks match, ignoring the value of number cards (see {@link Deck}).
 */
final class State {
    private final Deck deckA;
    private final Deck deckB;

    State(final Deck deckA, final Deck deckB) {
        this.deckA = deckA;
        this.deckB = deckB;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final State state)) return false;
        return Objects.equals(deckA, state.deckA) && Objects.equals(deckB, state.deckB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deckA, deckB);
    }
}
