/**
 * 📌 Problem: Maximum Subarray (LeetCode 53)
 *
 * Given an integer array nums, find the contiguous subarray
 * with the largest sum and return that sum.
 *
 * -------------------------------------------------------
 * 🔍 Clarification:
 * - Subarray must be contiguous
 * - Can contain negative numbers
 * - At least one element must be chosen
 *
 * Example:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6 → subarray [4,-1,2,1]
 *
 * -------------------------------------------------------
 * 🚀 Approach: Kadane’s Algorithm
 *
 * Idea:
 * - At each step, decide:
 *   → Start new subarray from current element
 *   → OR extend previous subarray
 *
 * Formula:
 * currentSum = max(nums[i], currentSum + nums[i])
 *
 * -------------------------------------------------------
 * ⏱️ Time Complexity:
 * - O(n) → single pass
 *
 * 🧠 Space Complexity:
 * - O(1)
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // either start new subarray or continue
            currentSum = Math.max(nums[i], currentSum + nums[i]);

            // update global maximum
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        MaximumSubarray obj = new MaximumSubarray();

        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};

        System.out.println(obj.maxSubArray(nums)); // Output: 6
    }
}
