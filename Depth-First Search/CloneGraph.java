/*
Optimized DFS Solution for Clone Graph

- Avoid double HashMap lookup
- Directly use get()
- Cleaner recursion flow

Time: O(N + E)
Space: O(N)
*/

class Solution {

    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {
        if (node == null) return null;

        // If already cloned, return it immediately
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // Create clone
        Node clone = new Node(node.val);
        map.put(node, clone);

        // Clone neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(dfs(neighbor, map));
        }

        return clone;
    }
}
