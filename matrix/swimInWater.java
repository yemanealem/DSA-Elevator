/*
===========================================
🔷 Swim in Rising Water (LeetCode 778)
===========================================

🧠 Idea:
Treat matrix as a graph.
Use Dijkstra-like approach where:
cost = max(current path cost, grid value)

Goal:
Minimize the maximum value along the path.

===========================================
⏱ Time: O(n^2 log n)
💾 Space: O(n^2)
===========================================
*/

import java.util.*;

class Solution {

    static class Node {
        int x, y, time;

        Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public int swimInWater(int[][] grid) {
        int n = grid.length;

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        boolean[][] visited = new boolean[n][n];

        PriorityQueue<Node> pq =
            new PriorityQueue<>(Comparator.comparingInt(a -> a.time));

        pq.offer(new Node(0, 0, grid[0][0]));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int x = cur.x, y = cur.y, time = cur.time;

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
                pq.offer(new Node(nx, ny, newTime));
            }
        }

        return -1;
    }
}
