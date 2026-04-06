/*
Question:
Given a reference node of a connected undirected graph, return a deep copy (clone) of the graph.

How it works:
- Use DFS to traverse the graph.
- Use a HashMap to store already cloned nodes:
    original node -> cloned node
- If a node is already cloned, return it (prevents infinite loops).
- Otherwise:
    1. Create a clone of the current node
    2. Store it in the map
    3. Recursively clone all neighbors and add them to the clone's neighbors list

Time Complexity: O(N + E)
Space Complexity: O(N)
*/

class Solution {

    private Map<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {

        if (node == null) return null;

        // If already cloned, return it
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // Create clone
        Node clone = new Node(node.val);
        map.put(node, clone);

        // Clone all neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }

        return clone;
    }
}
