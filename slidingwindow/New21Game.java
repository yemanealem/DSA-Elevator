public class New21Game {

    /*
     * LeetCode 837: New 21 Game
     * 
     * Problem:
     * Alice starts with 0 points. She draws numbers from 1 to maxPts uniformly at random
     * and adds to her score until she reaches at least k points. Once she reaches k, she stops drawing.
     * 
     * Return the probability that Alice has n or fewer points.
     * 
     * Example:
     * Input: n = 21, k = 17, maxPts = 10
     * Output: probability Alice has <= 21 points
     * 
     * Approach:
     * - Use DP: dp[i] = probability Alice has exactly i points
     * - dp[i] = sum(dp[i-1] + dp[i-2] + ... + dp[i-maxPts]) / maxPts
     * - Use sliding window sum to compute efficiently
     * - Only consider dp[i] for i <= n
     */

    public double new21Game(int n, int k, int maxPts) {
        if (k == 0 || n >= k + maxPts) return 1.0; // edge cases

        double[] dp = new double[n + 1];
        dp[0] = 1.0; // start at 0 points

        double windowSum = 1.0; // sum of last maxPts dp values
        double result = 0.0;

        for (int i = 1; i <= n; i++) {
            dp[i] = windowSum / maxPts; // probability for dp[i]

            if (i < k) {
                windowSum += dp[i]; // Alice can still draw
            } else {
                result += dp[i];    // Alice stops, add to result
            }

            if (i - maxPts >= 0) {
                windowSum -= dp[i - maxPts]; // remove probability outside the window
            }
        }

        return result;
    }

    public static void main(String[] args) {
        New21Game solution = new New21Game();

        int n = 21;
        int k = 17;
        int maxPts = 10;

        double probability = solution.new21Game(n, k, maxPts);

        System.out.println("Input: n = " + n + ", k = " + k + ", maxPts = " + maxPts);
        System.out.println("Probability Alice has <= " + n + " points: " + probability);
    }
}
