/**
 * Maximum Average Subarray I
 *
 * Problem:
 * Given an array nums and an integer k, find the contiguous subarray
 * of length k that has the maximum average value and return this value.
 *
 * Approach: Sliding Window
 *
 * How it works:
 * 1. Calculate the sum of the first 'k' elements (initial window).
 * 2. Slide the window through the array by 1 element at a time:
 *      - Remove the element that leaves the window (nums[i - k])
 *      - Add the new element that enters the window (nums[i])
 *      - Keep track of the maximum sum encountered.
 * 3. At the end, divide the maximum sum by k to get the maximum average.
 *
 * Example:
 * nums = [1, 12, -5, -6, 50, 3], k = 4
 * Initial sum = 1 + 12 - 5 - 6 = 2
 * Slide window:
 *   [12, -5, -6, 50] sum = 51 -> maxSum = 51
 *   [-5, -6, 50, 3] sum = 42 -> maxSum still 51
 * Maximum average = 51 / 4 = 12.75
 *
 * Running Time:
 * - O(n) because each element is added and removed exactly once.
 * - Space Complexity: O(1) because we only use a few variables.
 */

class MaximumAverageSubarray {

    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) sum += nums[i];  // sum of first window

        int maxSum = sum;

        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];  // slide window
            if (sum > maxSum) maxSum = sum; // track max sum
        }

        return maxSum / (double) k;  // convert to average
    }

    public static void main(String[] args) {
        MaximumAverageSubarray sol = new MaximumAverageSubarray();
        int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 4;
        System.out.println("Maximum average subarray: " + sol.findMaxAverage(nums, k));
        // Output: 12.75
    }
}
