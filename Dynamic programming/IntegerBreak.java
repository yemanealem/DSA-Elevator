/*
===========================================================
📌 PROBLEM: Integer Break

Given an integer n, break it into at least two positive integers
to maximize their product.

Return the maximum product possible.

===========================================================
🧠 IDEA (DP)

For each number i:
Try all splits j + (i - j)

We take max of:
1. j * (i - j)
2. j * dp[i - j]

===========================================================
⏱ TIME COMPLEXITY:
O(n²)

📦 SPACE COMPLEXITY:
O(n)

===========================================================
*/

public class IntegerBreak {

    public static int integerBreak(int n) {

        int[] dp = new int[n + 1];

        dp[1] = 1;

        for (int i = 2; i <= n; i++) {

            for (int j = 1; j < i; j++) {

                int noSplit = j * (i - j);
                int split = j * dp[i - j];

                dp[i] = Math.max(dp[i], Math.max(noSplit, split));
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {

        int n = 10;
        System.out.println(integerBreak(n)); 
    }
}