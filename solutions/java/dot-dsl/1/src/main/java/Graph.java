import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Graph {
    private final List<Node> nodes;
    private final List<Edge> edges;
    private final Map<String, String> attributes;

    public Graph() {
        this(Collections.emptyMap());
    }

    public Graph(final Map<String, String> attributes) {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.attributes = attributes;
    }

    public Collection<Node> getNodes() {
        return this.nodes;
    }

    public Collection<Edge> getEdges() {
        return this.edges;
    }

    public Graph node(final String name) {
        return this.node(name, Collections.emptyMap());
    }

    public Graph node(final String name, final Map<String, String> attributes) {
        this.nodes.add(new Node(name, attributes));
        return this;
    }

    public Graph edge(final String start, final String end) {
        return this.edge(start, end, Collections.emptyMap());
    }

    public Graph edge(final String start, final String end, final Map<String, String> attributes) {
        this.edges.add(new Edge(start, end, attributes));
        return this;
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }
}
