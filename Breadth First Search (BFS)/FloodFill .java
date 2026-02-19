/*
Flood Fill (LeetCode)

Problem:
Given a 2D image array where image[i][j] represents the color of the pixel,
a starting pixel (sr, sc), and a new color, replace the color of the starting pixel
and all 4-directionally connected pixels with the same original color with the new color.

Example:
Input:
image = [
  [1,1,1],
  [1,1,0],
  [1,0,1]
], sr = 1, sc = 1, newColor = 2

Output:
[
  [2,2,2],
  [2,2,0],
  [2,0,1]
]

How it works (trace):
1. Start at (1,1) with original color 1.
2. Change (1,1) to 2.
3. Explore 4 directions:
   - Up: (0,1) color 1 → change to 2
     - Up: (-1,1) out of bounds → stop
     - Down: (1,1) already 2 → stop
     - Left: (0,0) color 1 → change to 2
     - Right: (0,2) color 1 → change to 2
   - Down: (2,1) color 0 → skip
   - Left: (1,0) color 1 → change to 2
   - Right: (1,2) color 0 → skip
4. Continue recursively for all connected 1's.
5. Stop when all connected pixels with original color are replaced.
*/

public class FloodFill {

    private static final int[][] DIRS = {{-1,0},{1,0},{0,-1},{0,1}};

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];
        if(originalColor != newColor){
            dfs(image, sr, sc, originalColor, newColor);
        }
        return image;
    }

    private void dfs(int[][] image, int x, int y, int originalColor, int newColor){
        image[x][y] = newColor;
        for(int[] dir : DIRS){
            int nx = x + dir[0];
            int ny = y + dir[1];
            if(nx >= 0 && nx < image.length && ny >= 0 && ny < image[0].length
               && image[nx][ny] == originalColor){
                dfs(image, nx, ny, originalColor, newColor);
            }
        }
    }

    public static void main(String[] args) {
        FloodFill ff = new FloodFill();
        int[][] image1 = {
            {1,1,1},
            {1,1,0},
            {1,0,1}
        };
        int sr = 1, sc = 1, newColor = 2;
        int[][] result = ff.floodFill(image1, sr, sc, newColor);

        for(int[] row : result){
            for(int val : row){
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
