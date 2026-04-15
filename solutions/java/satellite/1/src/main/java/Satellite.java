import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Satellite {
    final Map<Character, Integer> inIdxByValue = new HashMap<>();
    int preIndex = 0;

    private static void validateInput(
            final List<Character> preorderInput, final List<Character> inorderInput) {
        if (preorderInput.size() != inorderInput.size()) {
            throw new IllegalArgumentException("traversals must have the same length");
        }

        final Set<Character> preorderSet = new HashSet<>(preorderInput);
        final Set<Character> inorderSet = new HashSet<>(inorderInput);
        if (!preorderSet.containsAll(inorderSet)) {
            throw new IllegalArgumentException("traversals must have the same elements");
        }

        if (preorderSet.size() != preorderInput.size()
                || inorderSet.size() != inorderInput.size()) {
            throw new IllegalArgumentException("traversals must contain unique items");
        }
    }

    Tree treeFromTraversals(
            final List<Character> preorderInput, final List<Character> inorderInput) {
        validateInput(preorderInput, inorderInput);

        preIndex = 0;
        for (int i = 0; i < preorderInput.size(); i++) {
            inIdxByValue.put(inorderInput.get(i), i);
        }

        final Node root = this.dfs(preorderInput, 0, preorderInput.size() - 1);

        return new Tree(root);
    }

    private Node dfs(final List<Character> preorder, final int left, final int right) {
        if (left > right) {
            return null;
        }

        final char rootValue = preorder.get(preIndex++);
        final Node root = new Node(rootValue);
        final int middle = inIdxByValue.get(rootValue);

        root.left = this.dfs(preorder, left, middle - 1);
        root.right = this.dfs(preorder, middle + 1, right);

        return root;
    }
}
