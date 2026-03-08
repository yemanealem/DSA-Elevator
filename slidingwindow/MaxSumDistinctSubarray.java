import java.util.*;

/**
 * Maximum Sum of Distinct Subarrays of Length K
 *
 * Optimized Sliding Window (Java):
 * - Uses HashMap to track frequency of elements in current window
 * - Updates current sum incrementally
 * - Only updates maxSum when all elements are distinct
 *
 * Time Complexity: O(n)
 * Space Complexity: O(k)
 */
class Solution {

    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> counterMap = new HashMap<>();
        long currentSum = 0;
        long maxSum = 0;

        // Initialize first window
        for (int i = 0; i < k; i++) {
            counterMap.put(nums[i], counterMap.getOrDefault(nums[i], 0) + 1);
            currentSum += nums[i];
        }
        if (counterMap.size() == k) maxSum = currentSum;

        // Slide window
        for (int i = k; i < n; i++) {
            int newNum = nums[i];
            int leftNum = nums[i - k];

            // Add new number
            counterMap.put(newNum, counterMap.getOrDefault(newNum, 0) + 1);
            currentSum += newNum;

            // Remove left number
            int leftCount = counterMap.get(leftNum) - 1;
            if (leftCount == 0) {
                counterMap.remove(leftNum);
            } else {
                counterMap.put(leftNum, leftCount);
            }
            currentSum -= leftNum;

            // Update maxSum if all elements in window are distinct
            if (counterMap.size() == k) maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 2, 7, 7, 4, 3, 6};
        int k = 3;
        System.out.println("Maximum sum of distinct subarray: " + sol.maximumSubarraySum(nums, k));
        // Output: 14
    }
}
