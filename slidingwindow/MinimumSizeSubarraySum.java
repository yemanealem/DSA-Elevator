/**
 * 📌 Problem: Minimum Size Subarray Sum (LeetCode 209)
 *
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a contiguous subarray of which the sum ≥ target.
 * If there is no such subarray, return 0 instead.
 *
 * -------------------------------------------------------
 * 🔍 Clarification:
 * - All numbers in nums are POSITIVE → this is very important
 * - We are looking for CONTIGUOUS subarray (not subsequence)
 * - We need the MINIMUM length, not the subarray itself
 *
 * Example:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2  → because subarray [4,3] has sum = 7
 *
 * -------------------------------------------------------
 * 🚀 Approach: Sliding Window (Two Pointers)
 *
 * Why it works:
 * - Since all numbers are positive:
 *   → Expanding window increases sum
 *   → Shrinking window decreases sum
 *
 * Steps:
 * 1. Expand window using 'right'
 * 2. When sum >= target:
 *      - Update minimum length
 *      - Shrink window from left
 *
 * -------------------------------------------------------
 * ⏱️ Time Complexity:
 * - O(n)
 *   Each element is visited at most twice:
 *   once when expanding (right pointer)
 *   once when shrinking (left pointer)
 *
 * 🧠 Space Complexity:
 * - O(1) → no extra space used
 *
 * -------------------------------------------------------
 */
public class MinimumSizeSubarraySum {

    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;                  // left pointer of window
        int sum = 0;                  // current window sum
        int minLength = Integer.MAX_VALUE; // store minimum length

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right]; // expand window

            // shrink window while condition is satisfied
            while (sum >= target) {
                // update minimum length
                minLength = Math.min(minLength, right - left + 1);

                // remove left element and move left pointer
                sum -= nums[left];
                left++;
            }
        }

        // if no valid subarray found, return 0
        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }

    public static void main(String[] args) {
        MinimumSizeSubarraySum obj = new MinimumSizeSubarraySum();

        int target = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};

        System.out.println(obj.minSubArrayLen(target, nums)); // Output: 2
    }
}
