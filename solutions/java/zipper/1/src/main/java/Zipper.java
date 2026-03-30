import java.util.Objects;

class Zipper {
    Zipper up;
    Zipper left;
    Zipper right;

    private int value;

    Zipper(final int val) {
        this.value = val;
    }

    BinaryTree toTree() {
        return up != null ? up.toTree() : new BinaryTree(this);
    }

    int getValue() {
        return value;
    }

    void setValue(final int val) {
        this.value = val;
    }

    Zipper setLeft(final Zipper leftChild) {
        this.left = leftChild;
        if (left != null) {
            left.up = this;
        }
        return this;
    }

    Zipper setRight(final Zipper rightChild) {
        this.right = rightChild;
        if (right != null) {
            right.up = this;
        }
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("value: ").append(value).append(", left: ");
        if (left != null) {
            sb.append("{ ").append(left).append(" }");
        } else {
            sb.append("null");
        }
        sb.append(", right: ");
        if (right != null) {
            sb.append("{ ").append(right).append(" }");
        } else {
            sb.append("null");
        }
        return sb.toString();
    }
}

class BinaryTree {
    private Zipper root;

    BinaryTree(final int value) {
        this.root = new Zipper(value);
    }

    BinaryTree(final Zipper root) {
        this.root = root;
    }

    Zipper getRoot() {
        return root;
    }

    String printTree() {
        return root.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final BinaryTree that)) return false;
        return Objects.equals(root, that.root);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(root);
    }
}
