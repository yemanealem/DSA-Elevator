/*
------------------------------------------------------------
📌 Problem: Maximal Square (Optimized)

Goal: Find largest square of '1's in binary matrix.

------------------------------------------------------------
🧠 Optimization Idea:

Instead of dp[i][j], use 1D DP:

dp[j] = current row state

We track:
- dp[j]     → left/top
- dp[j-1]   → diagonal
- prev      → previous row value

This reduces space from O(m*n) → O(n)

------------------------------------------------------------
⏱️ Time Complexity:
O(m × n)

📦 Space Complexity:
O(n)

------------------------------------------------------------
*/

class MaximalSquareOptimized {

    public int maximalSquare(char[][] matrix) {

        if (matrix == null || matrix.length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] dp = new int[cols + 1];

        int maxSide = 0;
        int prev = 0; // diagonal value

        for (int i = 1; i <= rows; i++) {

            prev = 0;

            for (int j = 1; j <= cols; j++) {

                int temp = dp[j]; 

                if (matrix[i - 1][j - 1] == '1') {

                    dp[j] = Math.min(
                            Math.min(dp[j], dp[j - 1]),
                            prev
                    ) + 1;

                    maxSide = Math.max(maxSide, dp[j]);

                } else {
                    dp[j] = 0;
                }

                prev = temp;
            }
        }

        return maxSide * maxSide;
    }
}

public class Main {

    public static void main(String[] args) {

        MaximalSquareOptimized solver = new MaximalSquareOptimized();

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