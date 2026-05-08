
/*
 * PROBLEM: Burst Balloons
 * ------------------------------------------
 * Given an array nums, each burst gives:
 * nums[left] * nums[i] * nums[right]
 *
 * Find maximum coins possible.
 *
 * KEY IDEA:
 * ------------------------------------------
 * Instead of choosing first balloon to burst,
 * choose the LAST balloon to burst in each interval.
 *
 * This converts problem into Interval DP.
 *
 * TIME COMPLEXITY:
 * O(n^3)
 *
 * SPACE COMPLEXITY:
 * O(n^2)
 */

class BurstBalloonsDP {

    public int maxCoins(int[] nums) {

        int n = nums.length;

        // Add virtual balloons (1 at both ends)
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

                for (int k = left + 1; k < right; k++) {

                    int coins = arr[left] * arr[k] * arr[right]
                            + dp[left][k]
                            + dp[k][right];

                    dp[left][right] = Math.max(dp[left][right], coins);
                }
            }
        }

        return dp[0][n + 1];
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        BurstBalloonsDP solver = new BurstBalloonsDP();

        int[] nums = {3, 1, 5, 8};

        int result = solver.maxCoins(nums);

        System.out.println("Max Coins: " + result);
    }
}