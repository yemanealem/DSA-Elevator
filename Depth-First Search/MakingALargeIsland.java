/*
827. Making A Large Island

You are given an n x n binary matrix grid.
You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.

--------------------------------------------------

Example 1:
Input:
grid = [[1,0],
        [0,1]]

Output: 3

Explanation:
Change one 0 to 1 and connect two islands.

--------------------------------------------------

Example 2:
Input:
grid = [[1,1],
        [1,0]]

Output: 4

Explanation:
Change the 0 to 1 to make the island bigger.

--------------------------------------------------

Example 3:
Input:
grid = [[1,1],
        [1,1]]

Output: 4

Explanation:
No need to change anything.

--------------------------------------------------

Approach (DFS):
1. Traverse the grid.
2. For every island:
   - Use DFS to calculate its size.
   - Assign a unique island ID.
   - Store island size in a HashMap.
3. Traverse all 0 cells:
   - Try converting 0 → 1.
   - Combine neighboring unique islands.
4. Return the maximum possible island size.

--------------------------------------------------

Time Complexity:
O(n^2)

Space Complexity:
O(n^2)
*/

import java.util.*;

public class MakingALargeIsland {

    private int n;

    private final int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    public int largestIsland(int[][] grid) {

        n = grid.length;

        Map<Integer, Integer> islandSizeMap = new HashMap<>();

        int islandId = 2;

        int maxIsland = 0;

        for (int row = 0; row < n; row++) {

            for (int col = 0; col < n; col++) {

                if (grid[row][col] == 1) {

                    int size = dfs(grid, row, col, islandId);

                    islandSizeMap.put(islandId, size);

                    maxIsland = Math.max(maxIsland, size);

                    islandId++;
                }
            }
        }

        for (int row = 0; row < n; row++) {

            for (int col = 0; col < n; col++) {

                if (grid[row][col] == 0) {

                    Set<Integer> visitedIslands = new HashSet<>();

                    int currentSize = 1;

                    for (int[] dir : directions) {

                        int newRow = row + dir[0];
                        int newCol = col + dir[1];

                        if (isValid(newRow, newCol)) {

                            int neighborIslandId = grid[newRow][newCol];

                            if (neighborIslandId > 1 &&
                                    visitedIslands.add(neighborIslandId)) {

                                currentSize += islandSizeMap.get(neighborIslandId);
                            }
                        }
                    }

                    maxIsland = Math.max(maxIsland, currentSize);
                }
            }
        }

        return maxIsland;
    }

    private int dfs(int[][] grid, int row, int col, int islandId) {

        if (!isValid(row, col) || grid[row][col] != 1) {
            return 0;
        }

        grid[row][col] = islandId;

        int size = 1;

        for (int[] dir : directions) {

            int newRow = row + dir[0];
            int newCol = col + dir[1];

            size += dfs(grid, newRow, newCol, islandId);
        }

        return size;
    }

    private boolean isValid(int row, int col) {

        return row >= 0 &&
                col >= 0 &&
                row < n &&
                col < n;
    }

    public static void main(String[] args) {

        MakingALargeIsland solution = new MakingALargeIsland();

        int[][] grid = {
                {1, 0},
                {0, 1}
        };

        int result = solution.largestIsland(grid);

        System.out.println("Largest Island Size: " + result);
    }
}