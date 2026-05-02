/*
===========================================
🔷 Problem: Cherry Pickup (LeetCode 741)
===========================================

You are given an n x n grid:
- 0 = empty cell
- 1 = cherry
- -1 = thorn (blocked cell)

You must:
1. Go from (0,0) → (n-1,n-1) collecting cherries
2. Return back from (n-1,n-1) → (0,0)
3. Only move right or down (forward logic)
4. Cannot step on -1
5. Each cherry can be collected only once

Goal: Maximize total cherries collected.

===========================================
🧠 How it works (Key Idea)
===========================================

Instead of solving forward + backward separately,
we simulate TWO people starting from (0,0) and moving
to (n-1,n-1) at the same time.

At step k:
- Person 1 is at (r1, c1)
- Person 2 is at (r2, c2)
- And: r1 + c1 = r2 + c2 (same number of steps)

We use DP:
dp[r1][c1][r2] = max cherries collected from these states

At each step, both can move:
- down/down
- right/right
- down/right
- right/down

If both land on same cell → count cherry once.

We use memoization to avoid recomputation.

===========================================
⏱ Time Complexity
===========================================

O(n^3)

Because:
- r1 (n states)
- c1 (n states)
- r2 (n states)

Each state checks 4 transitions → constant factor.

===========================================
*/

class CherryPickupSolver {

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

        // boundary check
        if (r1 >= n || c1 >= n || r2 >= n || c2 >= n)
            return Integer.MIN_VALUE;

        // thorn check
        if (grid[r1][c1] == -1 || grid[r2][c2] == -1)
            return Integer.MIN_VALUE;

        // reached destination
        if (r1 == n - 1 && c1 == n - 1)
            return grid[r1][c1];

        if (dp[r1][c1][r2] != null)
            return dp[r1][c1][r2];

        int cherries = grid[r1][c1];

        // avoid double counting
        if (r1 != r2 || c1 != c2)
            cherries += grid[r2][c2];

        int best = Math.max(
            Math.max(dfs(grid, r1 + 1, c1, r2 + 1),
                     dfs(grid, r1, c1 + 1, r2)),
            Math.max(dfs(grid, r1 + 1, c1, r2),
                     dfs(grid, r1, c1 + 1, r2 + 1))
        );

        return dp[r1][c1][r2] = cherries + best;
    }

    // ============================
    // Main method for testing
    // ============================
    public static void main(String[] args) {

        CherryPickupSolver solver = new CherryPickupSolver();

        int[][] grid = {
            {0, 1, -1},
            {1, 0, -1},
            {1, 1,  1}
        };

        int result = solver.cherryPickup(grid);

        System.out.println("Maximum cherries collected: " + result);
    }
}
