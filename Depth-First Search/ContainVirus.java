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

    // Main method
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