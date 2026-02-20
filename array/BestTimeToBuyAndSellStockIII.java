/*
LeetCode Problem: Best Time to Buy and Sell Stock III
Problem:
You are given an array prices where prices[i] is the price of a given stock on day i.
You can complete at most two transactions (buy one and sell one share of the stock).
You cannot engage in multiple transactions simultaneously (must sell before buying again).
Goal: Maximize your total profit.

Example:
Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation:
Buy on day 3 (price = 0) and sell on day 5 (price = 3), profit = 3
Then buy on day 6 (price = 1) and sell on day 7 (price = 4), profit = 3
Total profit = 3 + 3 = 6
*/

public class BestTimeToBuyAndSellStockIII {

    // Method to calculate max profit with at most 2 transactions
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        // Initialize four states
        int firstBuy = Integer.MIN_VALUE;   // Max profit after first buy
        int firstSell = 0;                  // Max profit after first sell
        int secondBuy = Integer.MIN_VALUE;  // Max profit after second buy
        int secondSell = 0;                 // Max profit after second sell

        for (int price : prices) {
            // Trace for each price:
            // Step 1: Consider buying first stock or keep previous state
            firstBuy = Math.max(firstBuy, -price);
            // Step 2: Consider selling first stock or keep previous state
            firstSell = Math.max(firstSell, firstBuy + price);
            // Step 3: Consider buying second stock (after first sell) or keep previous state
            secondBuy = Math.max(secondBuy, firstSell - price);
            // Step 4: Consider selling second stock or keep previous state
            secondSell = Math.max(secondSell, secondBuy + price);

            // Uncomment below to see step by step trace
            // System.out.println("Price: " + price + 
            //    " | firstBuy: " + firstBuy + 
            //    " | firstSell: " + firstSell +
            //    " | secondBuy: " + secondBuy +
            //    " | secondSell: " + secondSell);
        }

        return secondSell; // Max profit after at most two transactions
    }

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int maxProfit = maxProfit(prices);
        System.out.println("Maximum profit: " + maxProfit); // Output: 6
    }
}

/*
Time Complexity: O(n)
- We iterate through the array of prices once, updating 4 variables each step.

Space Complexity: O(1)
- Only 4 integer variables are used.

Step by Step Trace Example (prices = [3,3,5,0,0,3,1,4]):

Price: 3
firstBuy = max(-inf, -3) = -3
firstSell = max(0, -3+3) = 0
secondBuy = max(-inf, 0-3) = -3
secondSell = max(0, -3+3) = 0

Price: 3
firstBuy = max(-3, -3) = -3
firstSell = max(0, -3+3) = 0
secondBuy = max(-3, 0-3) = -3
secondSell = max(0, -3+3) = 0

Price: 5
firstBuy = max(-3, -5) = -3
firstSell = max(0, -3+5) = 2
secondBuy = max(-3, 2-5) = -3
secondSell = max(0, -3+5) = 2

... continue for all prices ...

Final Result: secondSell = 6
*/
