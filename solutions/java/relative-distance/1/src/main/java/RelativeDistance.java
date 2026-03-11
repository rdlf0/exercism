import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class RelativeDistance {
    final Map<String, Set<String>> edges = new HashMap<>();

    RelativeDistance(final Map<String, List<String>> familyTree) {
        for (final String parent : familyTree.keySet()) {
            for (final String child : familyTree.get(parent)) {
                edges.computeIfAbsent(parent, _ -> new HashSet<>()).add(child);
                edges.computeIfAbsent(child, _ -> new HashSet<>()).add(parent);

                for (final String sibling : familyTree.get(parent)) {
                    if (sibling.equals(child)) {
                        continue;
                    }
                    edges.computeIfAbsent(child, _ -> new HashSet<>()).add(sibling);
                    edges.computeIfAbsent(sibling, _ -> new HashSet<>()).add(child);
                }
            }
        }
    }

    int degreeOfSeparation(final String personA, final String personB) {
        final Queue<String> queue = new ArrayDeque<>();
        queue.offer(personA);

        final Set<String> visited = new HashSet<>();
        visited.add(personA);

        int levels = 0;
        while (!queue.isEmpty()) {
            final int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                final String current = queue.poll();

                if (current != null && current.equals(personB)) {
                    return levels;
                }

                for (final String child : edges.get(current)) {
                    if (!visited.contains(child)) {
                        queue.offer(child);
                        visited.add(child);
                    }
                }
            }
            levels++;
        }

        return -1;
    }
}
