/*
Problem: Number of Restricted Paths From First to Last Node

Question:
Given n nodes and a list of weighted edges, count the number of paths
from node 1 to node n such that for every move u → v:
distanceToN[u] > distanceToN[v]

How it works:
1. Run Dijkstra from node n to compute the shortest distance from every node to n.
2. Use DFS + Memoization (DP) starting from node 1:
   - Only move to neighbors with strictly smaller distance.
   - Cache results to avoid recomputation.

Key Insight:
- The distance condition forms a Directed Acyclic Graph (DAG)
  because distances strictly decrease.
- This allows safe DFS with memoization.

Time Complexity:
- Dijkstra: O(E log V)
- DFS + DP: O(V + E)
- Total: O(E log V)

Space Complexity:
- O(V + E)
*/

import java.util.*;

public class RestrictedPaths {

    static final int MOD = 1_000_000_007;

    public int countRestrictedPaths(int n, int[][] edges) {
        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            graph[e[1]].add(new int[]{e[0], e[2]});
        }

        // Step 1: Dijkstra from node n
        int[] dist = dijkstra(n, graph);

        // Step 2: DFS + Memoization
        Integer[] dp = new Integer[n + 1];
        return dfs(1, n, graph, dist, dp);
    }

    private int[] dijkstra(int start, List<int[]>[] graph) {
        int n = graph.length - 1;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{start, 0});
        dist[start] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int d = curr[1];

            if (d > dist[node]) continue;

            for (int[] nei : graph[node]) {
                int next = nei[0];
                int weight = nei[1];

                if (dist[next] > d + weight) {
                    dist[next] = d + weight;
                    pq.offer(new int[]{next, dist[next]});
                }
            }
        }

        return dist;
    }

    private int dfs(int node, int n, List<int[]>[] graph, int[] dist, Integer[] dp) {
        if (node == n) return 1;

        if (dp[node] != null) return dp[node];

        long ways = 0;

        for (int[] nei : graph[node]) {
            int next = nei[0];

            if (dist[node] > dist[next]) {
                ways += dfs(next, n, graph, dist, dp);
                ways %= MOD;
            }
        }

        return dp[node] = (int) ways;
    }

    // ✅ Main method for testing
    public static void main(String[] args) {
        RestrictedPaths solution = new RestrictedPaths();

        int n = 5;
        int[][] edges = {
            {1, 2, 3},
            {1, 3, 3},
            {2, 3, 1},
            {1, 4, 2},
            {5, 2, 2},
            {3, 5, 1},
            {5, 4, 10}
        };

        int result = solution.countRestrictedPaths(n, edges);
        System.out.println("Number of Restricted Paths: " + result);
    }
}
