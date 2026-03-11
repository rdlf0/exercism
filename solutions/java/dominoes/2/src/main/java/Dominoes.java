import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dominoes {

    private static final String CHAIN_NOT_FOUND_EXCEPTION = "No domino chain found.";

    List<Domino> formChain(final List<Domino> dominoes) throws ChainNotFoundException {
        final LinkedList<Domino> chain = new LinkedList<>();
        chain.add(dominoes.remove(0));
        return formChain(dominoes, chain);
    }

    private List<Domino> formChain(
        final List<Domino> dominoes,
        final LinkedList<Domino> chain) throws ChainNotFoundException {

        if (dominoes.isEmpty()) {
            if (chain.isEmpty() ||
                chain.getFirst().getLeft() == chain.getLast().getRight()) {
                return chain;
            }

            throw new ChainNotFoundException(CHAIN_NOT_FOUND_EXCEPTION);
        }

        final ArrayList<Domino> dominoesCopy = new ArrayList<>(dominoes);
        final LinkedList<Domino> chainCopy = new LinkedList<>(chain);
        final Domino chainLast = chainCopy.getLast();

        for (int i = 0; i < dominoesCopy.size(); i++) {
            final Domino domino = dominoesCopy.get(i);
            if (domino.getLeft() == chainLast.getRight() ||
                domino.getRight() == chainLast.getRight()) {
                if (domino.getRight() == chainLast.getRight()) {
                    domino.flip();
                }

                chainCopy.add(dominoesCopy.remove(i));

                try {
                    return formChain(dominoesCopy, chainCopy);
                } catch (ChainNotFoundException e) {
                    dominoesCopy.add(chainCopy.remove(chainCopy.size() - 1));
                }
            }
        }

        throw new ChainNotFoundException(CHAIN_NOT_FOUND_EXCEPTION);
    }

}
