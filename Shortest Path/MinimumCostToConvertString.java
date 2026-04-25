/*
------------------------------------------------------------
🧠 PROBLEM: Minimum Cost to Convert String I (LeetCode 2976)

You are given:
- source string
- target string
- conversion rules:
    original[i] -> changed[i] with cost[i]

You can apply multiple transformations.

Goal:
👉 Convert source → target with minimum total cost
👉 If impossible, return -1

------------------------------------------------------------
💡 HOW IT WORKS (KEY IDEA)

👉 Treat each character ('a' - 'z') as a graph node (26 nodes)

👉 Each conversion rule is a directed weighted edge:
    a -> b with cost c

👉 We need the shortest path between ALL character pairs

So we use:
    🔥 Floyd-Warshall Algorithm (All-Pairs Shortest Path)

Why?
- Only 26 nodes → very small
- We precompute all shortest transformations

------------------------------------------------------------
⚡ ALGORITHM STEPS

1. Initialize dist[i][j]
   - 0 if same character
   - INF otherwise

2. Add edges from conversion rules

3. Run Floyd-Warshall:
   try improving paths via intermediate nodes

4. Compute answer by summing:
   source[i] → target[i]

------------------------------------------------------------
⏱ TIME COMPLEXITY

Let:
    N = 26 (fixed)

Floyd-Warshall: O(N³) = constant (~17k ops)
String traversal: O(L)

Overall: O(26³ + L) → very fast in practice

------------------------------------------------------------
*/

public class MinimumCostToConvertString {

    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {

        int INF = (int) 1e9;
        int n = 26;

        int[][] dist = new int[n][n];

        // initialize distances
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = (i == j) ? 0 : INF;
            }
        }

        // build graph
        for (int i = 0; i < original.length; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }

        // Floyd-Warshall (all pairs shortest path)
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    if (dist[i][k] < INF && dist[k][j] < INF) {
                        dist[i][j] = Math.min(
                            dist[i][j],
                            dist[i][k] + dist[k][j]
                        );
                    }
                }
            }
        }

        // compute final answer
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

    // ---------------- MAIN METHOD ----------------
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
