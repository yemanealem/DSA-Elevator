import java.util.*;

/**
 * -------------------------------------------------------
 * Pacific Atlantic Water Flow
 * -------------------------------------------------------
 *
 * Question:
 *
 * You are given an m x n matrix of heights.
 *
 * The Pacific Ocean touches:
 * - left border
 * - top border
 *
 * The Atlantic Ocean touches:
 * - right border
 * - bottom border
 *
 * Water can flow from a cell to another cell if:
 * - the next cell height is smaller or equal
 *
 * Return all coordinates where water can flow
 * to BOTH the Pacific and Atlantic oceans.
 *
 * -------------------------------------------------------
 * How It Works:
 * -------------------------------------------------------
 *
 * Instead of starting DFS from every cell,
 * we reverse the process.
 *
 * We start from the oceans and move inward.
 *
 * Pacific DFS:
 * - start from top row and left column
 *
 * Atlantic DFS:
 * - start from bottom row and right column
 *
 * During DFS:
 * we can move only to:
 *
 * nextHeight >= currentHeight
 *
 * because we are reversing water flow.
 *
 * Finally:
 * Any cell visited by BOTH oceans
 * is part of the answer.
 *
 * -------------------------------------------------------
 * Time Complexity:
 * -------------------------------------------------------
 *
 * O(m × n)
 *
 * Each cell is visited at most:
 * - once by Pacific DFS
 * - once by Atlantic DFS
 *
 * -------------------------------------------------------
 * Space Complexity:
 * -------------------------------------------------------
 *
 * O(m × n)
 *
 * for visited arrays and recursion stack.
 *
 * -------------------------------------------------------
 */

public class PacificAtlanticWaterFlow {

    private int ROWS;
    private int COLS;

    // Directions:
    // down, up, right, left
    private final int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        List<List<Integer>> result = new ArrayList<>();

        if (heights == null || heights.length == 0) {
            return result;
        }

        ROWS = heights.length;
        COLS = heights[0].length;

        // visited arrays
        boolean[][] pacific = new boolean[ROWS][COLS];
        boolean[][] atlantic = new boolean[ROWS][COLS];

        // DFS from left and right borders
        for (int r = 0; r < ROWS; r++) {

            // Pacific -> left border
            dfs(heights, pacific, r, 0);

            // Atlantic -> right border
            dfs(heights, atlantic, r, COLS - 1);
        }

        // DFS from top and bottom borders
        for (int c = 0; c < COLS; c++) {

            // Pacific -> top border
            dfs(heights, pacific, 0, c);

            // Atlantic -> bottom border
            dfs(heights, atlantic, ROWS - 1, c);
        }

        // Find cells reachable by both oceans
        for (int r = 0; r < ROWS; r++) {

            for (int c = 0; c < COLS; c++) {

                if (pacific[r][c] && atlantic[r][c]) {

                    result.add(Arrays.asList(r, c));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights,
                     boolean[][] visited,
                     int row,
                     int col) {

        visited[row][col] = true;

        for (int[] dir : directions) {

            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // boundary check
            if (newRow < 0 || newCol < 0
                    || newRow >= ROWS
                    || newCol >= COLS) {
                continue;
            }

            // already visited
            if (visited[newRow][newCol]) {
                continue;
            }

            // move only to same or higher height
            if (heights[newRow][newCol]
                    < heights[row][col]) {
                continue;
            }

            dfs(heights, visited, newRow, newCol);
        }
    }

    public static void main(String[] args) {

        int[][] heights = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };

        PacificAtlanticWaterFlow solution =
                new PacificAtlanticWaterFlow();

        List<List<Integer>> result =
                solution.pacificAtlantic(heights);

        System.out.println(result);
    }
}