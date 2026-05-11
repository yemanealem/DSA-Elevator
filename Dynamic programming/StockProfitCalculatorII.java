import java.util.*;

/*
------------------------------------------------------------
📌 Problem: Best Time to Buy and Sell Stock II

You are allowed to complete as many transactions as you want.
You must sell before buying again.

Goal: Maximize profit.

------------------------------------------------------------
🧠 How it works:

We only care about upward price movements.

If today's price is greater than yesterday:
    we add the difference to profit.

This simulates:
- buying at local minimum
- selling at local maximum

No need for complex DP.

------------------------------------------------------------
⏱️ Time Complexity:
- O(n) → single pass through array

📦 Space Complexity:
- O(1)

------------------------------------------------------------
*/

class StockProfitCalculatorII {

    public int maxProfit(int[] prices) {

        int profit = 0;

        for (int i = 1; i < prices.length; i++) {

            // If price goes up, take the profit
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }

        return profit;
    }
}

/*
------------------------------------------------------------
🚀 Main class
------------------------------------------------------------
*/
public class Main {

    public static void main(String[] args) {

        StockProfitCalculatorII calculator = new StockProfitCalculatorII();

        int[] prices = {7, 1, 5, 3, 6, 4};

        int result = calculator.maxProfit(prices);

        System.out.println("Maximum Profit: " + result);
    }
}