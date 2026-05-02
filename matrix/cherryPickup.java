class Solution {

    private Integer[][][] dp;
    private int n;

    public int cherryPickup(int[][] grid) {
        n = grid.length;
        dp = new Integer[n][n][n];

        int result = dfs(grid, 0, 0, 0);
        return Math.max(0, result);
    }

    private int dfs(int[][] grid, int r1, int c1, int r2) {
        int c2 = r1 + c1 - r2;

        // bounds check
        if (r1 >= n || c1 >= n || r2 >= n || c2 >= n)
            return Integer.MIN_VALUE;

        // thorn check
        if (grid[r1][c1] == -1 || grid[r2][c2] == -1)
            return Integer.MIN_VALUE;

        // reached end
        if (r1 == n - 1 && c1 == n - 1)
            return grid[r1][c1];

        if (dp[r1][c1][r2] != null)
            return dp[r1][c1][r2];

        int cherries = grid[r1][c1];

        if (r1 != r2 || c1 != c2)
            cherries += grid[r2][c2];

        int best = Math.max(
            Math.max(dfs(grid, r1 + 1, c1, r2 + 1),
                     dfs(grid, r1, c1 + 1, r2)),
            Math.max(dfs(grid, r1 + 1, c1, r2),
                     dfs(grid, r1, c1 + 1, r2 + 1))
        );

        cherries += best;

        return dp[r1][c1][r2] = cherries;
    }
}
