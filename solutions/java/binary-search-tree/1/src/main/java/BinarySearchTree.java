import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

class BinarySearchTree<T extends Comparable<T>> {
    Node<T> root = null;

    void insert(final T value) {
        final Node<T> newNode = new Node<>(value);
        if (root == null) {
            root = newNode;
            return;
        }

        Node<T> current = root;
        while (true) {
            if (value.compareTo(current.getData()) <= 0) {
                if (current.getLeft() == null) {
                    current.setLeft(newNode);
                    break;
                }
                current = current.getLeft();
            } else {
                if (current.getRight() == null) {
                    current.setRight(newNode);
                    break;
                }
                current = current.getRight();
            }
        }
    }

    List<T> getAsSortedList() {
        if (root == null) {
            return Collections.emptyList();
        }

        final Deque<Node<T>> stack = new ArrayDeque<>();
        final List<T> result = new ArrayList<>();
        Node<T> current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }

            current = stack.pop();
            result.add(current.getData());
            current = current.getRight();
        }

        return result;
    }

    List<T> getAsLevelOrderList() {
        if (root == null) {
            return Collections.emptyList();
        }

        final Queue<Node<T>> queue = new ArrayDeque<>();
        queue.offer(root);

        final List<T> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            final Node<T> current = queue.poll();
            result.add(current.getData());

            Optional.ofNullable(current.getLeft()).ifPresent(queue::offer);
            Optional.ofNullable(current.getRight()).ifPresent(queue::offer);
        }

        return result;
    }

    Node<T> getRoot() {
        return root;
    }

    static final class Node<T> {
        private final T data;
        private Node<T> left;
        private Node<T> right;

        Node(final T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(final Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(final Node<T> right) {
            this.right = right;
        }
    }
}
