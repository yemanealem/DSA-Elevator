/**
 * Maximum Average Subarray I
 * Optimized Sliding Window
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class MaximumAverageSubarray {

    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) sum += nums[i];  // first window

        int maxSum = sum;

        // Slide window, avoid extra operations
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];  // remove left, add right
            if (sum > maxSum) maxSum = sum;  // avoid Math.max call overhead
        }

        return maxSum / (double) k;  // single cast at the end
    }

    public static void main(String[] args) {
        MaximumAverageSubarray sol = new MaximumAverageSubarray();
        int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 4;
        System.out.println(sol.findMaxAverage(nums, k));  // 12.75
    }
}
