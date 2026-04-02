public class UniquePathsII {

    /*
     * LeetCode: Unique Paths II
     *
     * QUESTION:
     * You are given an m x n grid filled with non-negative numbers where:
     *   0 represents an empty cell
     *   1 represents an obstacle
     *
     * A robot is located at the top-left corner (0, 0).
     * The robot can only move either DOWN or RIGHT at any point in time.
     *
     * The robot is trying to reach the bottom-right corner (m-1, n-1).
     *
     * Return the number of unique paths the robot can take to reach the destination,
     * while avoiding obstacles.
     *
     * ------------------------------------------------------------
     * HOW IT WORKS (Dynamic Programming - 1D Optimization):
     *
     * We use a 1D array dp[] where:
     *   dp[j] = number of ways to reach column j in the current row
     *
     * Steps:
     * 1. Initialize dp[0]:
     *      If starting cell has obstacle → dp[0] = 0
     *      Else → dp[0] = 1
     *
     * 2. Traverse the grid row by row:
     *      - If cell has obstacle → dp[j] = 0 (no path)
     *      - Else:
     *          dp[j] = dp[j] (from top) + dp[j-1] (from left)
     *
     *      Note:
     *      - dp[j] already stores "top" value (previous row)
     *      - dp[j-1] is the "left" value
     *
     * 3. Final answer will be in dp[n-1]
     *
     * ------------------------------------------------------------
     * EXAMPLE:
     * Grid:
     * 0 0 0
     * 0 1 0
     * 0 0 0
     *
     * Output: 2
     *
     * ------------------------------------------------------------
     * TIME COMPLEXITY:
     * O(m × n)
     * We visit every cell exactly once.
     *
     * SPACE COMPLEXITY:
     * O(n)
     * We only use a 1D array instead of full 2D DP table.
     *
     */

    public static int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] dp = new int[n];

        // Starting point
        dp[0] = (grid[0][0] == 1) ? 0 : 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {
                    dp[j] = 0; // obstacle blocks path
                } else if (j > 0) {
                    dp[j] += dp[j - 1]; // left + top
                }
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };

        System.out.println(uniquePathsWithObstacles(grid)); // Output: 2
    }
}
