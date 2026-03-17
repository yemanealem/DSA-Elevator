
/**
 * 📌 Problem: Maximum Sum Circular Subarray (LeetCode 918)
 *
 * Given a circular array nums, return the maximum possible sum
 * of a non-empty subarray.
 *
 * -------------------------------------------------------
 * 🔍 Clarification:
 * - Subarray must be contiguous
 * - Can wrap around (circular)
 * - Must pick at least one element
 *
 * Example:
 * Input: nums = [1,-2,3,-2]
 * Output: 3
 *
 * Input: nums = [5,-3,5]
 * Output: 10 → [5,5] (wrap-around)
 *
 * -------------------------------------------------------
 * 🚀 Approach: Kadane’s Algorithm (2 passes)
 *
 * Case 1:
 * - Normal max subarray using Kadane
 *
 * Case 2:
 * - Circular max = totalSum - minSubarray
 *
 * -------------------------------------------------------
 * ⏱️ Time Complexity:
 * - O(n)
 *
 * 🧠 Space Complexity:
 * - O(1)
 */
public class MaximumCircularSubarray {

    public int maxSubarraySumCircular(int[] nums) {

        int totalSum = 0;

        int currentMax = 0;
        int maxSum = Integer.MIN_VALUE;

        int currentMin = 0;
        int minSum = Integer.MAX_VALUE;

        for (int num : nums) {
            // Standard Kadane (max)
            currentMax = Math.max(num, currentMax + num);
            maxSum = Math.max(maxSum, currentMax);

            // Kadane for min subarray
            currentMin = Math.min(num, currentMin + num);
            minSum = Math.min(minSum, currentMin);

            totalSum += num;
        }

        // Edge case: all numbers are negative
        if (totalSum == minSum) {
            return maxSum;
        }

        // return max of normal vs circular
        return Math.max(maxSum, totalSum - minSum);
    }

    public static void main(String[] args) {
        MaximumCircularSubarray obj = new MaximumCircularSubarray();

        int[] nums = {5, -3, 5};

        System.out.println(obj.maxSubarraySumCircular(nums)); // Output: 10
    }
}
