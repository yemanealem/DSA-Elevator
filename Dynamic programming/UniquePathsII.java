public class UniquePathsII {

    public static int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] dp = new int[n];

        // Start position
        dp[0] = (grid[0][0] == 1) ? 0 : 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {
                    dp[j] = 0; // obstacle
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
