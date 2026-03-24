import java.util.Deque;
import java.util.LinkedList;

class DoublyLinkedList<T> {
    private final Deque<T> linkedList = new LinkedList<>();

    void push(final T value) {
        linkedList.addFirst(value);
    }

    T pop() {
        return linkedList.removeFirst();
    }

    void unshift(final T value) {
        linkedList.addLast(value);
    }

    T shift() {
        return linkedList.removeLast();
    }
}
