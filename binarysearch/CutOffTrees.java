/*
    LeetCode 675 - Cut Off Trees for Golf Event

    🧩 Problem:
    Given a forest grid:
        0 → obstacle
        1 → walkable ground
        >1 → tree with height

    👉 Cut all trees in increasing order of height
    👉 Return minimum steps required
    👉 If unreachable → return -1

    ------------------------------------------------------------

    💡 Approach:
    1. Collect all trees (value > 1)
    2. Sort trees by height
    3. Start from (0,0)
    4. For each tree:
        - Use BFS to find shortest path
        - Move to that tree
        - Add steps

    ------------------------------------------------------------

    ⏱️ Time Complexity:
        - Sorting: O(T log T), T = number of trees
        - BFS per tree: O(m * n)
        - Total: O(T * m * n)

    🧠 Space Complexity:
        - O(m * n) for visited array

    ------------------------------------------------------------

    ❗ Not Binary Search:
        - Requires shortest path traversal (BFS)
*/

import java.util.*;

public class CutOffTrees {

    public static int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size();
        int n = forest.get(0).size();

        // Step 1: Collect trees
        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{forest.get(i).get(j), i, j});
                }
            }
        }

        // Step 2: Sort by height
        Collections.sort(trees, (a, b) -> a[0] - b[0]);

        int totalSteps = 0;
        int startX = 0, startY = 0;

        // Step 3: Process each tree
        for (int[] tree : trees) {
            int targetX = tree[1];
            int targetY = tree[2];

            int steps = bfs(forest, startX, startY, targetX, targetY);

            if (steps == -1) return -1;

            totalSteps += steps;
            startX = targetX;
            startY = targetY;
        }

        return totalSteps;
    }

    // BFS to find shortest path
    private static int bfs(List<List<Integer>> forest,
                           int sx, int sy, int tx, int ty) {

        if (sx == tx && sy == ty) return 0;

        int m = forest.size();
        int n = forest.get(0).size();

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1], steps = curr[2];

            for (int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx >= 0 && ny >= 0 &&
                    nx < m && ny < n &&
                    !visited[nx][ny] &&
                    forest.get(nx).get(ny) != 0) {

                    if (nx == tx && ny == ty) {
                        return steps + 1;
                    }

                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, steps + 1});
                }
            }
        }

        return -1; // unreachable
    }

    public static void main(String[] args) {
        List<List<Integer>> forest = Arrays.asList(
            Arrays.asList(1,2,3),
            Arrays.asList(0,0,4),
            Arrays.asList(7,6,5)
        );

        System.out.println(cutOffTree(forest)); // Output: 6
    }
}
