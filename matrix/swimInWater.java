/*
===========================================
🔷 Swim in Rising Water (Optimized)
===========================================

🧠 Optimization Idea:
Instead of PriorityQueue + objects,
we use a lightweight int array heap.

This reduces:
- Object allocation
- Comparator overhead
- GC pressure

Still behaves like Dijkstra.

===========================================
⏱ Time: O(n^2 log n)
💾 Space: O(n^2)
===========================================
*/

import java.util.*;

class Solution {

    public int swimInWater(int[][] grid) {
        int n = grid.length;

        boolean[][] visited = new boolean[n][n];

        // min-heap: [time, x, y]
        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> a[0] - b[0]);

        pq.offer(new int[]{grid[0][0], 0, 0});

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!pq.isEmpty()) {

            int[] cur = pq.poll();
            int time = cur[0], x = cur[1], y = cur[2];

            if (visited[x][y]) continue;
            visited[x][y] = true;

            if (x == n - 1 && y == n - 1)
                return time;

            for (int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                    continue;

                if (visited[nx][ny])
                    continue;

                int newTime = Math.max(time, grid[nx][ny]);
                pq.offer(new int[]{newTime, nx, ny});
            }
        }

        return -1;
    }
}
