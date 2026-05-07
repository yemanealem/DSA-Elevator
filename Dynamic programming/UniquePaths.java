public class UniquePaths {

    /*
     * ============================================================
     * PROBLEM:
     * ============================================================
     * A robot is located at the top-left corner of an m x n grid.
     *
     * The robot can only move:
     * 1. Right
     * 2. Down
     *
     * Find the total number of unique paths
     * from top-left to bottom-right.
     *
     * ------------------------------------------------------------
     * EXAMPLE:
     * ------------------------------------------------------------
     *
     * Input:
     * m = 3
     * n = 7
     *
     * Output:
     * 28
     *
     * ============================================================
     * HOW DYNAMIC PROGRAMMING WORKS:
     * ============================================================
     *
     * We use:
     *
     * dp[i][j]
     *
     * Meaning:
     * Number of unique ways to reach cell (i, j)
     *
     * ------------------------------------------------------------
     * CORE IDEA:
     * ------------------------------------------------------------
     *
     * To reach any cell,
     * robot can come from:
     *
     * 1. Top cell
     * 2. Left cell
     *
     * Therefore:
     *
     * dp[i][j] = dp[i-1][j] + dp[i][j-1]
     *
     * ------------------------------------------------------------
     * BASE CASE:
     * ------------------------------------------------------------
     *
     * First row:
     * Only move RIGHT
     *
     * First column:
     * Only move DOWN
     *
     * So all values become 1.
     *
     * ============================================================
     * TIME COMPLEXITY:
     * ============================================================
     *
     * Time Complexity:
     * O(m × n)
     *
     * Space Complexity:
     * O(m × n)
     *
     * ============================================================
     * JAVA SOLUTION:
     * ============================================================
     */

    public int uniquePaths(int m, int n) {

        // DP table
        int[][] dp = new int[m][n];

        /*
         * Fill first row with 1
         * Only one way to move right
         */
        for (int j = 0; j < n; j++) {

            dp[0][j] = 1;
        }

        /*
         * Fill first column with 1
         * Only one way to move down
         */
        for (int i = 0; i < m; i++) {

            dp[i][0] = 1;
        }

        /*
         * Fill remaining cells
         */
        for (int i = 1; i < m; i++) {

            for (int j = 1; j < n; j++) {

                dp[i][j] =
                        dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // Final answer
        return dp[m - 1][n - 1];
    }

    /*
     * ============================================================
     * MAIN METHOD:
     * ============================================================
     */
    public static void main(String[] args) {

        UniquePaths solution = new UniquePaths();

        int m = 3;
        int n = 7;

        System.out.println(
                "Grid Size: " + m + " x " + n);

        int result = solution.uniquePaths(m, n);

        System.out.println(
                "Unique Paths: " + result);
    }
}