public class FindTheTownJudge {

    public int findJudge(int n, int[][] trust) {
        int[] trustCount = new int[n + 1];
        int[] trustedBy = new int[n + 1];

        // How it works:
        // trust[i] = {a, b} means person a trusts person b.
        // The judge:
        // - trusts nobody (trustCount[judge] == 0)
        // - is trusted by everyone else (trustedBy[judge] == n - 1)

        for (int[] t : trust) {
            trustCount[t[0]]++;
            trustedBy[t[1]]++;
        }

        for (int i = 1; i <= n; i++) {
            if (trustCount[i] == 0 && trustedBy[i] == n - 1) {
                return i; // This is the town judge
            }
        }

        return -1; // No judge found
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
     * Question:
     * Find the town judge. In the town, if there is a judge:
     * - The judge trusts nobody.
     * - Everyone else trusts the judge.
     *
     * How it works (Graph Theory):
     * We use two arrays:
     * - trustCount[i]: how many people i trusts.
     * - trustedBy[i]: how many people trust i.
     *
     * The judge condition:
     * - trustCount[judge] == 0 (does not trust anyone)
     * - trustedBy[judge] == n - 1 (everyone else trusts judge)
     *
     * Trace Example:
     * n = 3
     * trust = { {1,3}, {2,3} }
     * trustCount[1] = 1, trustedBy[3] = 2
     * trustCount[2] = 1, trustedBy[3] = 2
     * trustCount[3] = 0
     * -> 3 is the judge
     *
     * Running Time:
     * O(n) because we iterate through trust array and check once.
     * Space Complexity:
     * O(n) for arrays.
     */
}
