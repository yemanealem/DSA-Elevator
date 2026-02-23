public class FindTheTownJudge {

    public int findJudge(int n, int[][] trust) {

        // Graph Theory Approach (Optimized):
        // The judge:
        // - Has indegree = n - 1 (everyone trusts the judge)
        // - Has outdegree = 0 (judge trusts no one)

        int[] indegree = new int[n + 1];
        int[] outdegree = new int[n + 1];

        for (int[] edge : trust) {
            int a = edge[0];
            int b = edge[1];

            outdegree[a]++;  // a trusts someone (so not judge)
            indegree[b]++;    // b is trusted
        }

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == n - 1 && outdegree[i] == 0) {
                return i; // This is the town judge
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        FindTheTownJudge solution = new FindTheTownJudge();

        int n = 3;
        int[][] trust = {
            {1, 3},
            {2, 3}
        };

        int judge = solution.findJudge(n, trust);

        System.out.println("Town Judge: " + judge);
    }

    /*
     * Question (Graph Theory):
     * In a directed graph:
     * - Each trust relationship is an edge: a -> b (a trusts b).
     * - The judge has:
     *   indegree = n - 1 (everyone points to judge)
     *   outdegree = 0 (judge points to no one)
     *
     * Why?
     * - If someone trusts the judge → incoming edge (indegree++)
     * - Judge trusts no one → no outgoing edges (outdegree = 0)
     *
     * Trace:
     * trust = { {1,3}, {2,3} }
     * indegree[3] = 2 (from 1 and 2)
     * outdegree[3] = 0
     * => 3 is the judge
     *
     * Running Time:
     * O(n)
     * Space Complexity:
     * O(n)
     */
}
