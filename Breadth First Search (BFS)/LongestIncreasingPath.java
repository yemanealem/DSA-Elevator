/*
Problem: Longest Increasing Path in a Matrix (LeetCode)

Given an m x n integer matrix, return the length of the longest increasing path in the matrix.

Rules:
- You can move in four directions: up, down, left, right.
- You cannot move diagonally or move outside the boundary.
- You can only move to a cell with a strictly larger value.

Example 1:
Input: matrix = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:
Input: matrix = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Output: 4
Explanation: The longest increasing path is [3,4,5,6].
*/

public class LongestIncreasingPath {

    // Directions for moving in the matrix: up, down, left, right
    private static final int[][] DIRS = {{-1,0},{1,0},{0,-1},{0,1}};

    /**
     * Main method to compute the longest increasing path in a matrix
     * @param matrix Input 2D integer matrix
     * @return Length of the longest increasing path
     */
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int m = matrix.length, n = matrix[0].length;
        int[][] memo = new int[m][n]; // memo[i][j] stores longest path starting from (i,j)
        int maxPath = 0;

        // Try starting DFS from each cell and take the maximum path
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                maxPath = Math.max(maxPath, dfs(matrix, i, j, memo));
            }
        }

        return maxPath;
    }

    /**
     * DFS function with memoization
     * @param matrix Input matrix
     * @param x Current row
     * @param y Current column
     * @param memo Memoization array to avoid recomputation
     * @return Length of longest increasing path starting from (x,y)
     */
    private int dfs(int[][] matrix, int x, int y, int[][] memo){
        // If already computed, return memoized result
        if(memo[x][y] != 0) return memo[x][y];

        int maxLen = 1; // At least the current cell counts as length 1

        // Explore all 4 directions
        for(int[] dir : DIRS){
            int nx = x + dir[0];
            int ny = y + dir[1];

            // Move to neighbor if it's inside the matrix and has a larger value
            if(nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix[0].length
               && matrix[nx][ny] > matrix[x][y]){
                maxLen = Math.max(maxLen, 1 + dfs(matrix, nx, ny, memo));
            }
        }

        // Store result in memo array
        memo[x][y] = maxLen;
        return maxLen;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        LongestIncreasingPath lip = new LongestIncreasingPath();

        // Example 1
        int[][] matrix1 = {
            {9,9,4},
            {6,6,8},
            {2,1,1}
        };

        // Example 2
        int[][] matrix2 = {
            {3,4,5},
            {3,2,6},
            {2,2,1}
        };

        System.out.println("Longest Increasing Path (matrix1): " + lip.longestIncreasingPath(matrix1)); // Output: 4
        System.out.println("Longest Increasing Path (matrix2): " + lip.longestIncreasingPath(matrix2)); // Output: 4
    }
}

/*
How it works:

1. We use DFS to explore all strictly increasing paths starting from each cell.
2. To avoid recomputation, we store the longest path starting from each cell in the memo array.
3. For each cell, we look in 4 directions:
   - If the neighbor has a greater value, we recursively compute the longest path from that neighbor.
   - We add 1 to include the current cell in the path length.
4. After exploring all cells, we return the maximum path length found.
5. This ensures each cell is visited only once, giving O(m*n) time complexity.
*/
