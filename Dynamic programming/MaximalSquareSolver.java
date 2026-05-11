import java.util.*;

/*
------------------------------------------------------------
📌 Problem: Maximal Square

Given a binary matrix, find the area of the largest square
containing only '1's.

------------------------------------------------------------
🧠 How it works (Dynamic Programming):

We define:
dp[i][j] = size of largest square ending at (i, j)

If matrix[i][j] == '1':
    dp[i][j] = min(top, left, diagonal) + 1
Else:
    dp[i][j] = 0

We track the maximum square size found.

------------------------------------------------------------
⏱️ Time Complexity:
- O(m * n) → each cell processed once

📦 Space Complexity:
- O(m * n) (can be optimized to O(n))

------------------------------------------------------------
*/

class MaximalSquareSolver {

    public int maximalSquare(char[][] matrix) {

        if (matrix == null || matrix.length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dp = new int[rows][cols];
        int maxSide = 0;

        // Initialize first row and column
        for (int i = 0; i < rows; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            maxSide = Math.max(maxSide, dp[i][0]);
        }

        for (int j = 0; j < cols; j++) {
            dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
            maxSide = Math.max(maxSide, dp[0][j]);
        }

        // Fill DP table
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {

                if (matrix[i][j] == '1') {

                    dp[i][j] = Math.min(
                            dp[i - 1][j],
                            Math.min(dp[i][j - 1], dp[i - 1][j - 1])
                    ) + 1;

                    maxSide = Math.max(maxSide, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        // Area = side²
        return maxSide * maxSide;
    }
}

/*
------------------------------------------------------------
🚀 Main class
------------------------------------------------------------
*/
public class Main {

    public static void main(String[] args) {

        MaximalSquareSolver solver = new MaximalSquareSolver();

        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        int result = solver.maximalSquare(matrix);

        System.out.println("Maximal Square Area: " + result);
    }
}