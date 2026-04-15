import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

final class ListOps {

    private ListOps() {
        // No instances.
    }

    static <T> List<T> append(final List<T> list1, final List<T> list2) {
        final List<T> result = new ArrayList<>(list1);
        result.addAll(list2);
        return result;
    }

    static <T> List<T> concat(final List<List<T>> listOfLists) {
        return listOfLists.stream().flatMap(List::stream).toList();
    }

    static <T> List<T> filter(final List<T> list, final Predicate<T> predicate) {
        return list.stream().filter(predicate).toList();
    }

    static <T> int size(final List<T> list) {
        return list.size();
    }

    static <T, U> List<U> map(final List<T> list, final Function<T, U> transform) {
        return list.stream().map(transform).toList();
    }

    static <T> List<T> reverse(final List<T> list) {
        return list.reversed();
    }

    static <T, U> U foldLeft(final List<T> list, final U initial, final BiFunction<U, T, U> f) {
        return list.stream()
                .reduce(
                        initial,
                        f,
                        (_, _) -> {
                            throw new UnsupportedOperationException();
                        });
    }

    static <T, U> U foldRight(final List<T> list, final U initial, final BiFunction<T, U, U> f) {
        U result = initial;
        final ListIterator<T> it = list.listIterator(list.size());
        while (it.hasPrevious()) {
            final T el = it.previous();
            result = f.apply(el, result);
        }

        return result;
    }
}
