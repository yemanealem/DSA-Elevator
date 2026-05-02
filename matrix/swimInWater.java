/*
===========================================
🔷 Swim in Rising Water (Fast Approach)
===========================================

🧠 Idea:
Binary search the answer (time T)
and check if we can reach (n-1,n-1)
using BFS only on cells <= T.

===========================================
⏱ Time: O(n^2 log(maxValue))
💾 Space: O(n^2)
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
}
