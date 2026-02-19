public class LongestIncreasingPath {

    private static final int[][] DIRS = {{-1,0},{1,0},{0,-1},{0,1}}; // up, down, left, right

    // DFS + Memoization method
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int m = matrix.length, n = matrix[0].length;
        int[][] memo = new int[m][n]; // memo[i][j] = longest path starting at (i,j)
        int maxPath = 0;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                maxPath = Math.max(maxPath, dfs(matrix, i, j, memo));
            }
        }

        return maxPath;
    }

    private int dfs(int[][] matrix, int x, int y, int[][] memo){
        if(memo[x][y] != 0) return memo[x][y]; // already computed

        int maxLen = 1; // at least the cell itself
        for(int[] dir : DIRS){
            int nx = x + dir[0], ny = y + dir[1];
            if(nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix[0].length
               && matrix[nx][ny] > matrix[x][y]){
                maxLen = Math.max(maxLen, 1 + dfs(matrix, nx, ny, memo));
            }
        }

        memo[x][y] = maxLen;
        return maxLen;
    }

    // Main method to test
    public static void main(String[] args) {
        LongestIncreasingPath lip = new LongestIncreasingPath();

        int[][] matrix1 = {
            {9,9,4},
            {6,6,8},
            {2,1,1}
        };

        int[][] matrix2 = {
            {3,4,5},
            {3,2,6},
            {2,2,1}
        };

        System.out.println("Longest Increasing Path (matrix1): " + lip.longestIncreasingPath(matrix1)); // Output: 4
        System.out.println("Longest Increasing Path (matrix2): " + lip.longestIncreasingPath(matrix2)); // Output: 4
    }
}
