class Solution {
    private int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}}; // up, down, left, right
    
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n]; // stores longest path starting at (i,j)
        
        int maxPath = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                maxPath = Math.max(maxPath, dfs(matrix, i, j, memo));
            }
        }
        return maxPath;
    }
    
    private int dfs(int[][] matrix, int i, int j, int[][] memo) {
        if(memo[i][j] != 0) return memo[i][j]; // already computed
        
        int maxLen = 1; // minimum path length starting at this cell
        for(int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            // move to neighbor only if value is increasing
            if(ni >= 0 && ni < matrix.length && nj >= 0 && nj < matrix[0].length 
               && matrix[ni][nj] > matrix[i][j]) {
                int len = 1 + dfs(matrix, ni, nj, memo);
                maxLen = Math.max(maxLen, len);
            }
        }
        
        memo[i][j] = maxLen; // memoize
        return maxLen;
    }
}
