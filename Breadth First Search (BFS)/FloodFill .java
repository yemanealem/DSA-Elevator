/*
Problem: Flood Fill (LeetCode)
Given an image represented as a 2D array and a starting pixel (sr, sc),
change the color of the starting pixel and all 4-directionally connected
pixels with the same original color to a new color.
*/

public class FloodFill {

    private static final int[][] DIRS = {{-1,0},{1,0},{0,-1},{0,1}}; // up, down, left, right

    /**
     * Flood fill the image starting from (sr, sc)
     * @param image Input image (2D array)
     * @param sr Starting row
     * @param sc Starting column
     * @param newColor Color to replace
     * @return Modified image
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];
        if(originalColor != newColor){
            dfs(image, sr, sc, originalColor, newColor);
        }
        return image;
    }

    /**
     * Recursive DFS helper function
     */
    private void dfs(int[][] image, int x, int y, int originalColor, int newColor){
        // Change color of current pixel
        image[x][y] = newColor;

        // Explore 4 directions
        for(int[] dir : DIRS){
            int nx = x + dir[0];
            int ny = y + dir[1];

            // Check boundaries and original color
            if(nx >= 0 && nx < image.length && ny >= 0 && ny < image[0].length
               && image[nx][ny] == originalColor){
                dfs(image, nx, ny, originalColor, newColor);
            }
        }
    }

    // Main method to test the solution
    public static void main(String[] args) {
        FloodFill ff = new FloodFill();

        int[][] image1 = {
            {1,1,1},
            {1,1,0},
            {1,0,1}
        };

        int sr = 1, sc = 1, newColor = 2;

        int[][] result = ff.floodFill(image1, sr, sc, newColor);

        System.out.println("Flood Fill Result:");
        for(int[] row : result){
            for(int val : row){
                System.out.print(val + " ");
            }
            System.out.println();
        }
        // Expected Output:
        // 2 2 2
        // 2 2 0
        // 2 0 1
    }
}

/*
How it works:

1. Start DFS at the pixel (sr, sc) with the original color.
2. Change the current pixel to newColor.
3. For each 4-directionally connected pixel:
   - If it has the original color, recursively apply DFS.
4. Recursion ensures all connected pixels of the original color are filled.
5. Stops automatically when reaching pixels with different color or boundaries.
*/
