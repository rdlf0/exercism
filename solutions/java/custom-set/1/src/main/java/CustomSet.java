import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class CustomSet<T> {
    private final Set<T> set;

    CustomSet() {
        this.set = new HashSet<>();
    }

    CustomSet(final Collection<T> data) {
        this.set = new HashSet<>(data);
    }

    boolean isEmpty() {
        return set.isEmpty();
    }

    boolean contains(final T element) {
        return set.contains(element);
    }

    boolean isDisjoint(final CustomSet<T> other) {
        return set.stream().noneMatch(other::contains);
    }

    boolean add(final T element) {
        return set.add(element);
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final CustomSet<?> customSet)) return false;
        return Objects.equals(set, customSet.set);
    }

    CustomSet<T> getIntersection(final CustomSet<T> other) {
        final CustomSet<T> intersection = new CustomSet<>(set);
        intersection.set.retainAll(other.set);
        return intersection;
    }

    CustomSet<T> getUnion(final CustomSet<T> other) {
        final CustomSet<T> union = new CustomSet<>(this.set);
        union.set.addAll(other.set);
        return union;
    }

    CustomSet<T> getDifference(final CustomSet<T> other) {
        final CustomSet<T> difference = new CustomSet<>(this.set);
        difference.set.removeAll(other.set);
        return difference;
    }

    boolean isSubset(final CustomSet<T> other) {
        return set.containsAll(other.set);
    }
}
