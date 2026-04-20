class Solution {

    /*
     * Problem:
     * Find the nth ugly number.
     * Ugly numbers have only prime factors: 2, 3, 5.
     *
     * Approach (DP with 3 pointers):
     * - Start with dp[0] = 1
     * - Use 3 pointers for multiples of 2, 3, and 5
     * - Generate next ugly number by taking minimum
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */

    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;

        int i2 = 0, i3 = 0, i5 = 0;

        for (int i = 1; i < n; i++) {
            int next2 = dp[i2] * 2;
            int next3 = dp[i3] * 3;
            int next5 = dp[i5] * 5;

            int next = Math.min(next2, Math.min(next3, next5));
            dp[i] = next;

            if (next == next2) i2++;
            if (next == next3) i3++;
            if (next == next5) i5++;
        }

        return dp[n - 1];
    }
}
