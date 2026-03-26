import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

class SimpleLinkedList<T> {
    private int size = 0;
    private Node<T> head;

    SimpleLinkedList() {}

    SimpleLinkedList(final T[] values) {
        Arrays.stream(values).forEach(this::push);
    }

    void push(final T value) {
        head = new Node<>(value, head);
        size++;
    }

    T pop() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        final Node<T> removed = head;
        head = head.getNext();
        removed.setNext(null);
        size--;

        return removed.getValue();
    }

    void reverse() {
        if (head == null) {
            return;
        }

        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            final Node<T> temp = current.getNext();
            current.setNext(prev);
            prev = current;
            current = temp;
        }
        head = prev;
    }

    @SuppressWarnings("unchecked")
    T[] asArray(final Class<T> clazz) {
        final T[] result = (T[]) Array.newInstance(clazz, size);
        Node<T> current = head;
        int i = 0;
        while (current != null) {
            result[i] = current.getValue();
            current = current.getNext();
            i++;
        }

        return result;
    }

    int size() {
        return size;
    }

    private static final class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(final T value, final Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(final Node<T> next) {
            this.next = next;
        }
    }
}
