
/*
------------------------------------------------------------
📌 Problem: Best Time to Buy and Sell Stock IV

You are given:
- An array "prices" where prices[i] is stock price on day i
- An integer k representing maximum number of transactions

Each transaction = (buy + sell)

Goal:
👉 Maximize profit with at most k transactions

------------------------------------------------------------
🧠 How it works (Dynamic Programming Idea):

We use DP where:

dp[i][j] = maximum profit using at most i transactions
           until day j

For each state, we decide:
1. Do nothing on day j → dp[i][j-1]
2. Sell on day j → prices[j] + best buy before

To optimize buying, we use:
maxDiff = max(dp[i-1][j] - prices[j])

This avoids O(n²) inner loops.

------------------------------------------------------------
⏱️ Time Complexity:
- O(k * n)
Where:
k = number of transactions
n = number of days

Space Complexity:
- O(k * n)

------------------------------------------------------------
*/

class StockProfitCalculator {

    public int maxProfit(int k, int[] prices) {

        int n = prices.length;
        if (n == 0 || k == 0) return 0;

        // If k is large, problem becomes unlimited transactions
        if (k >= n / 2) {
            int profit = 0;

            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }

        // DP table: dp[i][j]
        int[][] dp = new int[k + 1][n];

        for (int i = 1; i <= k; i++) {

            // maxDiff stores best (dp[i-1][j] - prices[j])
            int maxDiff = -prices[0];

            for (int j = 1; j < n; j++) {

                // Option 1: don't sell today
                // Option 2: sell today using best previous buy
                dp[i][j] = Math.max(
                        dp[i][j - 1],
                        prices[j] + maxDiff
                );

                // Update best buy opportunity
                maxDiff = Math.max(
                        maxDiff,
                        dp[i - 1][j] - prices[j]
                );
            }
        }

        return dp[k][n - 1];
    }
}

/*
------------------------------------------------------------
🚀 Main class to run the program
------------------------------------------------------------
*/
public class Main {

    public static void main(String[] args) {

        StockProfitCalculator calculator = new StockProfitCalculator();

        int k = 2;
        int[] prices = {3, 2, 6, 5, 0, 3};

        int result = calculator.maxProfit(k, prices);

        System.out.println("Maximum Profit: " + result);
    }
}