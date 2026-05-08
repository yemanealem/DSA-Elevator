
/*
 * PROBLEM: Burst Balloons (LeetCode Hard)
 * -------------------------------------------------------
 * You are given an array nums of balloons.
 * When you burst balloon i, you gain:
 *
 *      nums[left] * nums[i] * nums[right]
 *
 * where left and right are adjacent remaining balloons.
 *
 * Goal: Find maximum coins possible by bursting all balloons.
 *
 * -------------------------------------------------------
 * HOW IT WORKS (KEY IDEA):
 * -------------------------------------------------------
 * Instead of choosing the FIRST balloon to burst,
 * we choose the LAST balloon to burst in a range.
 *
 * This transforms the problem into INTERVAL DP:
 *
 * dp[left][right] = max coins obtainable in (left, right)
 *
 * For every interval, we try all possible last balloons k:
 *
 * dp[left][right] =
 *     max(dp[left][k] + dp[k][right] +
 *         arr[left] * arr[k] * arr[right])
 *
 * -------------------------------------------------------
 * WHY THIS WORKS:
 * -------------------------------------------------------
 * Fixing the last balloon avoids dependency issues
 * and makes subproblems independent.
 *
 * -------------------------------------------------------
 * TIME COMPLEXITY:
 * -------------------------------------------------------
 * O(n^3)
 * - O(n^2) intervals
 * - O(n) choices per interval
 *
 * SPACE COMPLEXITY:
 * -------------------------------------------------------
 * O(n^2) for DP table
 */

class BurstBalloonsOptimized {

    public int maxCoins(int[] nums) {

        int n = nums.length;

        // Add virtual balloons at both ends
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;

        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }

        int[][] dp = new int[n + 2][n + 2];

        // length of interval
        for (int len = 2; len < n + 2; len++) {

            for (int left = 0; left < n + 2 - len; left++) {

                int right = left + len;

                int best = 0;

                int leftVal = arr[left];
                int rightVal = arr[right];

                // try all possible last balloons
                for (int k = left + 1; k < right; k++) {

                    int coins =
                            leftVal * arr[k] * rightVal +
                            dp[left][k] +
                            dp[k][right];

                    best = Math.max(best, coins);
                }

                dp[left][right] = best;
            }
        }

        return dp[0][n + 1];
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        BurstBalloonsOptimized solver = new BurstBalloonsOptimized();

        int[] nums = {3, 1, 5, 8};

        System.out.println("Max Coins: " + solver.maxCoins(nums));
    }
}