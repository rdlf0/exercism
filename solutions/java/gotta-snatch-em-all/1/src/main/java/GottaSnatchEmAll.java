import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

final class GottaSnatchEmAll {

    private GottaSnatchEmAll() {}

    static Set<String> newCollection(final List<String> cards) {
        return new HashSet<>(cards);
    }

    static boolean addCard(final String card, final Set<String> collection) {
        return collection.add(card);
    }

    static boolean canTrade(final Set<String> myCollection, final Set<String> theirCollection) {
        return !myCollection.containsAll(theirCollection)
                && !theirCollection.containsAll(myCollection);
    }

    static Set<String> commonCards(final List<Set<String>> collections) {
        if (collections == null || collections.isEmpty()) {
            return Collections.emptySet();
        }

        final Set<String> result = new HashSet<>(collections.getFirst());
        if (result.isEmpty()) {
            return Collections.emptySet();
        }

        collections.forEach(result::retainAll);

        return result;
    }

    static Set<String> allCards(final List<Set<String>> collections) {
        if (collections == null || collections.isEmpty()) {
            return Collections.emptySet();
        }

        return collections.stream()
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
