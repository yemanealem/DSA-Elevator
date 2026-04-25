import java.util.*;

public class MinimumCostToConvertString {

    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {

        int INF = (int) 1e9;
        int n = 26;

        int[][] dist = new int[n][n];

        // initialize distance matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = (i == j) ? 0 : INF;
            }
        }

        // build graph from rules
        for (int i = 0; i < original.length; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }

        // Floyd-Warshall (all-pairs shortest path)
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] < INF && dist[k][j] < INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // compute answer
        long ans = 0;

        for (int i = 0; i < source.length(); i++) {
            int s = source.charAt(i) - 'a';
            int t = target.charAt(i) - 'a';

            if (s != t) {
                if (dist[s][t] == INF) return -1;
                ans += dist[s][t];
            }
        }

        return ans;
    }

    // MAIN METHOD
    public static void main(String[] args) {

        MinimumCostToConvertString obj = new MinimumCostToConvertString();

        String source = "abc";
        String target = "bcd";

        char[] original = {'a', 'b', 'c'};
        char[] changed  = {'b', 'c', 'd'};
        int[] cost      = {1, 1, 1};

        long result = obj.minimumCost(source, target, original, changed, cost);

        System.out.println("Minimum Cost: " + result);
    }
}
