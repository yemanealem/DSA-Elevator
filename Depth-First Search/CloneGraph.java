/*
Question:
Given a reference node of a connected undirected graph, return a deep copy (clone) of the graph.

How it works:
- Use DFS to traverse the graph.
- Use a HashMap to store already cloned nodes (avoid cycles).
- If node already cloned → return it.
- Otherwise:
    1. Create clone
    2. Store in map
    3. Recursively clone neighbors

Time Complexity: O(N + E)
Space Complexity: O(N)
*/

class CloneGraph {

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {

        // If already cloned, return immediately
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // Create clone and store it
        Node clone = new Node(node.val);
        map.put(node, clone);

        // Clone neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(dfs(neighbor, map));
        }

        return clone;
    }
}
