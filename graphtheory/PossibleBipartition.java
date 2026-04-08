\/*
Problem: Possible Bipartition (LeetCode)

Goal:
Determine if we can divide people into 2 groups such that
no pair in dislikes is in the same group.

How it works:
- Build graph (adjacency list)
- Use coloring (1 and -1)
- Traverse using BFS
- If a neighbor has the same color → return false

Time Complexity: O(n + dislikes.length)
Space Complexity: O(n + dislikes.length)
*/

import java.util.*;

public class PossibleBipartition {

    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<List<Integer>> graph = new ArrayList<>();

        // Initialize graph
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Build adjacency list
        for (int[] d : dislikes) {
            graph.get(d[0]).add(d[1]);
            graph.get(d[1]).add(d[0]);
        }

        int[] color = new int[n + 1]; // 0 = unvisited, 1 = group A, -1 = group B

        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                if (!bfs(graph, color, i)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean bfs(List<List<Integer>> graph, int[] color, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color[start] = 1;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : graph.get(node)) {
                if (color[neighbor] == 0) {
                    color[neighbor] = -color[node];
                    queue.add(neighbor);
                } else if (color[neighbor] == color[node]) {
                    return false;
                }
            }
        }

        return true;
    }

    // Main method for testing
    public static void main(String[] args) {
        PossibleBipartition solution = new PossibleBipartition();

        int n = 4;
        int[][] dislikes = {{1,2}, {1,3}, {2,4}};

        System.out.println(solution.possibleBipartition(n, dislikes)); // true
    }
}
