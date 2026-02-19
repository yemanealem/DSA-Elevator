/*
Problem: Island Perimeter (LeetCode)
Given a grid representing land (1) and water (0), find the perimeter of the island.
*/

public class IslandPerimeter {

    /**
     * Compute the perimeter of the island
     * @param grid Input 2D integer grid
     * @return Perimeter of the island
     */
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        int m = grid.length;
        int n = grid[0].length;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    perimeter += 4; // initial 4 edges for the cell

                    // Check top neighbor
                    if(i > 0 && grid[i-1][j] == 1) perimeter -= 2;
                    // Check left neighbor
                    if(j > 0 && grid[i][j-1] == 1) perimeter -= 2;
                }
            }
        }

        return perimeter;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        IslandPerimeter ip = new IslandPerimeter();

        int[][] grid1 = {
            {0,1,0,0},
            {1,1,1,0},
            {0,1,0,0},
            {1,1,0,0}
        };

        int[][] grid2 = {
            {1}
        };

        int[][] grid3 = {
            {1,0}
        };

        System.out.println("Island Perimeter (grid1): " + ip.islandPerimeter(grid1)); // Output: 16
        System.out.println("Island Perimeter (grid2): " + ip.islandPerimeter(grid2)); // Output: 4
        System.out.println("Island Perimeter (grid3): " + ip.islandPerimeter(grid3)); // Output: 4
    }
}

/*
How it works:

1. For each land cell, assume it has 4 edges.
2. For every neighbor that is also land (top or left), subtract 2 from perimeter.
   - Subtract 2 because the shared edge is counted twice.
3. Continue for all land cells.
4. Return the final perimeter.

Example grid1 trace:
- Cell (0,1) adds 4 → perimeter = 4
- Cell (1,0) adds 4 → perimeter = 8
- Cell (1,1) adds 4, has top and left land neighbors → subtract 4 → perimeter = 8
- Continue similarly → final perimeter = 16
*/
