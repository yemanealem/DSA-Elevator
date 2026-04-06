import java.util.*;

/*
Question:
Given a reference node of a connected undirected graph, return a deep copy (clone) of the graph.

How it works:
- We use Depth-First Search (DFS) to traverse the graph.
- A HashMap is used to store already cloned nodes:
    original node -> cloned node
- If a node is already visited (exists in map), we return the cloned node immediately.
- Otherwise:
    1. Create a clone of the current node
    2. Store it in the map
    3. Recursively clone all neighbors and add them to the clone

Running Time:
- Time Complexity: O(N + E)
  (N = number of nodes, E = number of edges)

- Space Complexity: O(N)
  (HashMap + recursion stack)
*/

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

class CloneGraph {

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {

        // If already cloned, return it
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

public class Main {

    public static void main(String[] args) {

        /*
        Graph:
            1 -- 2
            |    |
            4 -- 3
        */

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        CloneGraph solution = new CloneGraph();
        Node cloned = solution.cloneGraph(node1);

        System.out.println("Original Node: " + node1.val);
        System.out.println("Cloned Node: " + cloned.val);

        System.out.println("Cloned Graph Neighbors of Node 1:");
        for (Node n : cloned.neighbors) {
            System.out.print(n.val + " ");
        }
    }
}
