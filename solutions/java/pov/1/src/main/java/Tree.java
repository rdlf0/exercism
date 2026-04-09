import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

class Tree {
    private final String label;
    private final List<Tree> children;

    public Tree(final String label) {
        this(label, new ArrayList<>());
    }

    public Tree(final String label, final List<Tree> children) {
        this.label = label;
        this.children = children;
    }

    public static Tree of(final String label) {
        return new Tree(label);
    }

    public static Tree of(final String label, final List<Tree> children) {
        return new Tree(label, children);
    }

    private static Map<String, List<String>> createAdjacencyMap(final Tree root) {
        final Map<String, List<String>> adj = new HashMap<>();
        final Queue<Tree> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            final Tree parent = queue.poll();
            adj.put(parent.label, new ArrayList<>());
            for (final Tree child : parent.children) {
                adj.get(parent.label).add(child.label);
                queue.offer(child);
            }
        }
        return adj;
    }

    private static void reroot(
            final String current, final String prev, final Map<String, List<String>> adj) {
        for (final String parent : adj.keySet()) {
            if (!parent.equals(prev) && adj.get(parent).contains(current)) {
                adj.get(parent).remove(current);
                adj.get(current).add(parent);
                reroot(parent, current, adj);
                break;
            }
        }
    }

    private static void buildFromAdjacencyMap(
            final Tree root, final Map<String, List<String>> adj) {
        final Queue<Tree> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            final Tree current = queue.poll();
            for (final String child : adj.get(current.label)) {
                final Tree childNode = Tree.of(child);
                current.children.add(childNode);
                queue.offer(childNode);
            }
        }
    }

    private static boolean findPath(final Tree node, final String target, final List<String> path) {
        if (node == null) {
            return false;
        }

        path.add(node.label);

        if (node.label.equals(target)) {
            return true;
        }

        for (final Tree child : node.children) {
            if (findPath(child, target, path)) {
                return true;
            }
        }

        path.removeLast();

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return label.equals(tree.label)
                && children.size() == tree.children.size()
                && children.containsAll(tree.children)
                && tree.children.containsAll(children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, children);
    }

    @Override
    public String toString() {
        return "Tree{" + label + ", " + children + "}";
    }

    public Tree fromPov(final String fromNode) {
        final Map<String, List<String>> adj = createAdjacencyMap(this);
        if (!adj.containsKey(fromNode)) {
            throw new UnsupportedOperationException("Tree could not be reoriented");
        }

        reroot(fromNode, null, adj);

        final Tree newRoot = Tree.of(fromNode);
        buildFromAdjacencyMap(newRoot, adj);

        return newRoot;
    }

    public List<String> pathTo(final String fromNode, final String toNode) {
        final Tree root;
        try {
            root = this.fromPov(fromNode);
        } catch (final UnsupportedOperationException e) {
            throw new UnsupportedOperationException("No path found");
        }

        final List<String> result = new ArrayList<>();
        findPath(root, toNode, result);

        if (result.isEmpty()) {
            throw new UnsupportedOperationException("No path found");
        }

        return result;
    }
}
