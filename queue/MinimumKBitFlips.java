/*
 * Problem: Minimum Number of K Consecutive Bit Flips (LeetCode 995)
 *
 * You are given a binary array nums (only 0 and 1) and an integer k.
 * You can flip exactly k consecutive bits (0 -> 1, 1 -> 0).
 *
 * Goal:
 * Return the minimum number of flips required to make all elements 1.
 * If it is impossible, return -1.
 *
 * ------------------------------------------------------------
 * How this solution works:
 * ------------------------------------------------------------
 * - Instead of flipping bits directly (which is slow), we track the "effect"
 *   of flips using a difference array.
 *
 * - We use:
 *     1. flip → keeps track of whether current index is flipped odd/even times
 *     2. diff[] → marks when a flip effect starts and ends
 *
 * - At each index i:
 *     1. Remove expired flip effect using diff[i]
 *     2. Check if current bit (after flips) is 0
 *     3. If 0, we MUST start a flip at i
 *        - Increase result
 *        - Mark flip ending at i + k
 *
 * ------------------------------------------------------------
 * Key Insight:
 * ------------------------------------------------------------
 * We never actually flip the array.
 * We only track whether flips affect current position (parity logic).
 *
 * ------------------------------------------------------------
 * Time Complexity:
 * ------------------------------------------------------------
 * Time:  O(n)  -> each index is processed once
 * Space: O(n)  -> difference array
 *
 * This is the optimal solution and much faster than queue-based approach.
 */

public class MinimumKBitFlips {

    public static int minKBitFlips(int[] nums, int k) {
        int n = nums.length;

        int[] diff = new int[n + 1]; // marks flip start/end
        int flip = 0;                // current flip parity (0 or 1)
        int res = 0;                 // number of flips used

        for (int i = 0; i < n; i++) {

            // remove expired flip effect
            flip ^= diff[i];

            // if current bit after flips is 0 → we must flip here
            if (nums[i] == flip) {

                // if flip goes out of bounds → impossible
                if (i + k > n) {
                    return -1;
                }

                res++;

                // start a new flip
                flip ^= 1;

                // mark when this flip effect ends
                diff[i + k] ^= 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {

        int[] nums = {0, 1, 0};
        int k = 1;

        int result = minKBitFlips(nums, k);

        System.out.println("Minimum flips required = " + result);
    }
}
