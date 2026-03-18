import java.util.Collections;
import java.util.List;

class BinarySearch {
    private final List<Integer> items;

    BinarySearch(final List<Integer> items) {
        this.items = items;
    }

    int indexOf(final int item) throws ValueNotFoundException {
        final int index = Collections.binarySearch(items, item);
        if (index < 0) {
            throw new ValueNotFoundException("Value not in array");
        }
        return index;
    }
}
