/*
===========================================
🔷 Swim in Rising Water (LeetCode 778)
===========================================

🧠 Problem:
You are given an n x n grid where each cell
represents elevation. Water rises over time.

You can move 4 directions and can only step
on cells where grid[i][j] <= time.

Goal:
Find minimum time to reach (n-1, n-1).

===========================================
💡 How it works:
1. Binary search the answer (time T)
2. For each T, run BFS to check if path exists
3. Return smallest valid T

Why this is efficient:
- Avoids PriorityQueue (Dijkstra overhead)
- Uses simple BFS checks
- Reduces search space via binary search

===========================================
⏱ Time Complexity: O(n^2 log(maxValue))
💾 Space Complexity: O(n^2)
===========================================
*/

import java.util.*;

class Solution {

    int n;
    int[][] grid;

    public int swimInWater(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;

        int left = grid[0][0];
        int right = n * n - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (canReach(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canReach(int limit) {

        if (grid[0][0] > limit)
            return false;

        boolean[][] visited = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{0, 0});
        visited[0][0] = true;

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!q.isEmpty()) {

            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            if (x == n - 1 && y == n - 1)
                return true;

            for (int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                    continue;

                if (visited[nx][ny])
                    continue;

                if (grid[nx][ny] > limit)
                    continue;

                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }

        return false;
    }

    // ============================
    // Main method for testing
    // ============================
    public static void main(String[] args) {

        Solution sol = new Solution();

        int[][] grid = {
            {0, 2},
            {1, 3}
        };

        System.out.println(
            "Minimum time to reach end: " + sol.swimInWater(grid)
        );
    }
}
