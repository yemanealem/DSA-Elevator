import java.util.*;

public class PacificAtlanticWaterFlow {

    private int ROWS;
    private int COLS;

    // Directions: up, down, left, right
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

        boolean[][] pacific = new boolean[ROWS][COLS];
        boolean[][] atlantic = new boolean[ROWS][COLS];

        // DFS from Pacific borders
        for (int r = 0; r < ROWS; r++) {
            dfs(heights, pacific, r, 0);
            dfs(heights, atlantic, r, COLS - 1);
        }

        // DFS from Atlantic borders
        for (int c = 0; c < COLS; c++) {
            dfs(heights, pacific, 0, c);
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

            if (newRow < 0 || newCol < 0
                    || newRow >= ROWS
                    || newCol >= COLS) {
                continue;
            }

            if (visited[newRow][newCol]) {
                continue;
            }

            if (heights[newRow][newCol] < heights[row][col]) {
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