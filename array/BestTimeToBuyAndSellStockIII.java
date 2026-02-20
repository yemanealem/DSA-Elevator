public class BestTimeToBuyAndSellStockIII {

    // Method to calculate max profit with at most 2 transactions
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int firstBuy = Integer.MIN_VALUE;
        int firstSell = 0;
        int secondBuy = Integer.MIN_VALUE;
        int secondSell = 0;

        for (int price : prices) {
            firstBuy = Math.max(firstBuy, -price);               // Max profit after first buy
            firstSell = Math.max(firstSell, firstBuy + price);  // Max profit after first sell
            secondBuy = Math.max(secondBuy, firstSell - price); // Max profit after second buy
            secondSell = Math.max(secondSell, secondBuy + price); // Max profit after second sell
        }

        return secondSell;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int maxProfit = maxProfit(prices);
        System.out.println("Maximum profit: " + maxProfit); // Output: 6
    }
}
