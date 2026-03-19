class DoublyLinkedList<T> {
    private Element<T> first;
    private Element<T> last;

    void push(final T value) {
        final Element<T> f = first;
        final Element<T> newNode = new Element<>(value, null, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
    }

    T pop() {
        final T value = first.value;
        final Element<T> next = first.next;
        first.next = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }

        return value;
    }

    void unshift(final T value) {
        final Element<T> l = last;
        final Element<T> newNode = new Element<>(value, l, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
    }

    T shift() {
        final Element<T> e = last;
        final Element<T> prev = e.prev;
        last.prev = null;
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }

        return e.value;
    }

    private static final class Element<T> {
        private final T value;
        private Element<T> prev;
        private Element<T> next;

        Element(final T value, final Element<T> prev, final Element<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
