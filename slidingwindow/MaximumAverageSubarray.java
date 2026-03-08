/**
 * Maximum Average Subarray I
 * Sliding Window approach
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class MaximumAverageSubarray {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        // Calculate sum of first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        
        int maxSum = windowSum;
        
        // Slide the window
        for (int i = k; i < n; i++) {
            windowSum = windowSum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, windowSum);
        }
        
        return (double) maxSum / k;
    }

    // Example main
    public static void main(String[] args) {
        MaximumAverageSubarray sol = new MaximumAverageSubarray();
        int[] nums = {1,12,-5,-6,50,3};
        int k = 4;
        double result = sol.findMaxAverage(nums, k);
        System.out.println("Maximum average subarray: " + result); // 12.75
    }
}
