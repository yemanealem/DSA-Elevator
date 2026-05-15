/*
===============================================================================
 LeetCode Problem: Contain Virus
===============================================================================

Question:
You are given an m x n grid where:

1  = infected cell
0  = healthy cell

Each day, the virus spreads to neighboring healthy cells
(up, down, left, right).

You can build walls around ONLY ONE infected region per day.

Your task:
- Find the infected region that will infect the largest number
  of healthy cells.
- Build walls around it (quarantine it).
- Let the other regions spread.
- Return the total number of walls built.

-------------------------------------------------------------------------------
How This DFS Solution Works
-------------------------------------------------------------------------------

1. Find all infected regions using DFS
   - DFS explores connected infected cells.
   - Each connected component is considered one region.

2. For every region calculate:
   - Frontier cells:
     Healthy cells this region can infect next.
   - Walls needed:
     Number of walls required to isolate the region.

3. Select the most dangerous region
   - The region threatening the most healthy cells.

4. Quarantine that region
   - Mark all cells as -1 (blocked).

5. Spread remaining regions
   - Other infected regions spread to neighboring healthy cells.

6. Repeat until no more spread is possible.

-------------------------------------------------------------------------------
DFS Idea
-------------------------------------------------------------------------------

DFS is used to:
- Traverse connected infected cells.
- Count required walls.
- Collect neighboring healthy cells.

-------------------------------------------------------------------------------
Time Complexity
-------------------------------------------------------------------------------

Let:
R = number of rows
C = number of columns

Each iteration scans the whole grid.

Worst Case:
Time Complexity  = O((R * C)^2)
Space Complexity = O(R * C)

-------------------------------------------------------------------------------
*/

import java.util.*;

public class ContainVirus {

    private int rows;
    private int cols;

    // 4 directions
    private final int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    public int containVirus(int[][] grid) {

        rows = grid.length;
        cols = grid[0].length;

        int totalWalls = 0;

        while (true) {

            List<List<int[]>> regions = new ArrayList<>();
            List<Set<Integer>> frontiers = new ArrayList<>();
            List<Integer> wallsNeeded = new ArrayList<>();

            boolean[][] visited = new boolean[rows][cols];

            // Find all infected regions
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {

                    if (grid[r][c] == 1 && !visited[r][c]) {

                        List<int[]> region = new ArrayList<>();
                        Set<Integer> frontier = new HashSet<>();

                        int[] walls = new int[1];

                        dfs(grid, r, c, visited,
                                region, frontier, walls);

                        regions.add(region);
                        frontiers.add(frontier);
                        wallsNeeded.add(walls[0]);
                    }
                }
            }

            // No regions left
            if (regions.isEmpty()) {
                break;
            }

            // Find region that infects most cells
            int maxIndex = 0;

            for (int i = 1; i < frontiers.size(); i++) {

                if (frontiers.get(i).size() >
                        frontiers.get(maxIndex).size()) {

                    maxIndex = i;
                }
            }

            // No spread possible
            if (frontiers.get(maxIndex).isEmpty()) {
                break;
            }

            // Build walls
            totalWalls += wallsNeeded.get(maxIndex);

            // Quarantine region
            for (int[] cell : regions.get(maxIndex)) {
                grid[cell[0]][cell[1]] = -1;
            }

            // Spread other regions
            for (int i = 0; i < regions.size(); i++) {

                if (i == maxIndex) {
                    continue;
                }

                for (int code : frontiers.get(i)) {

                    int r = code / cols;
                    int c = code % cols;

                    grid[r][c] = 1;
                }
            }
        }

        return totalWalls;
    }

    private void dfs(int[][] grid,
                     int r,
                     int c,
                     boolean[][] visited,
                     List<int[]> region,
                     Set<Integer> frontier,
                     int[] walls) {

        // Boundary check
        if (r < 0 || c < 0 ||
                r >= rows || c >= cols) {
            return;
        }

        // Already visited or not infected
        if (visited[r][c] || grid[r][c] != 1) {
            return;
        }

        visited[r][c] = true;

        region.add(new int[]{r, c});

        for (int[] dir : directions) {

            int nr = r + dir[0];
            int nc = c + dir[1];

            // Healthy neighbor
            if (nr >= 0 && nc >= 0 &&
                    nr < rows && nc < cols &&
                    grid[nr][nc] == 0) {

                walls[0]++;

                frontier.add(nr * cols + nc);
            }

            // Continue DFS
            else if (nr >= 0 && nc >= 0 &&
                    nr < rows && nc < cols &&
                    grid[nr][nc] == 1 &&
                    !visited[nr][nc]) {

                dfs(grid, nr, nc, visited,
                        region, frontier, walls);
            }
        }
    }

    // Main Method
    public static void main(String[] args) {

        ContainVirus solution = new ContainVirus();

        int[][] grid = {
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        int result = solution.containVirus(grid);

        System.out.println("Total walls built: " + result);
    }
}