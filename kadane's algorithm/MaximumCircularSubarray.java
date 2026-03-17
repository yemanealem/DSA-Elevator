public class MaximumCircularSubarray {

    /**
     * 📌 Problem: Maximum Sum Circular Subarray
     *
     * Approach:
     * 1. Find normal max subarray using Kadane
     * 2. If all numbers are negative → return maxKadane
     * 3. Invert array → find min subarray using Kadane
     * 4. maxWrap = totalSum + kadane(inverted)
     * 5. Return max(maxKadane, maxWrap)
     *
     * ⏱️ Time: O(n)
     * 🧠 Space: O(n) (because of inverted array)
     */

    public int maxSubarraySumCircular(int[] nums) {

        if (nums == null || nums.length == 0) return 0;

        // Step 1: Normal Kadane
        int maxKadane = kadane(nums);

        // 🚨 Step 2: All negative case
        if (maxKadane < 0) {
            return maxKadane;
        }

        int totalSum = 0;
        int[] inverted = new int[nums.length];

        // Step 3: Build inverted array + compute total sum
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
            inverted[i] = -nums[i];
        }

        // Step 4: Kadane on inverted array
        int maxInverted = kadane(inverted);

        // maxWrap = totalSum - minSubarray
        int maxWrap = totalSum + maxInverted;

        // Step 5: Final answer
        return Math.max(maxKadane, maxWrap);
    }

    // Standard Kadane's Algorithm
    private int kadane(int[] arr) {
        int currentMax = arr[0];
        int globalMax = arr[0];

        for (int i = 1; i < arr.length; i++) {
            currentMax = Math.max(arr[i], currentMax + arr[i]);
            globalMax = Math.max(globalMax, currentMax);
        }

        return globalMax;
    }

    public static void main(String[] args) {
        MaximumCircularSubarray obj = new MaximumCircularSubarray();

        int[] nums1 = {5, -3, 5};
        System.out.println(obj.maxSubarraySumCircular(nums1)); // 10

        int[] nums2 = {1, -2, 3, -2};
        System.out.println(obj.maxSubarraySumCircular(nums2)); // 3

        int[] nums3 = {-3, -2, -3};
        System.out.println(obj.maxSubarraySumCircular(nums3)); // -2
    }
}
