/*
LeetCode: Maximum Gap

Question:
Given an integer array nums, return the maximum difference between two successive 
elements in its sorted form. If the array contains less than 2 elements, return 0.

How it works (Radix Sort):
1. Use Radix Sort (base 10) to sort the array in O(n) time.
2. Process digits from least significant to most significant.
3. Use Counting Sort for each digit (0–9).
4. After sorting, scan once to find the maximum gap between adjacent elements.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class MaximumGapRadix {

    public static int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        int max = nums[0];
        for (int num : nums) {
            if (num > max) max = num;
        }

        int exp = 1;
        int n = nums.length;
        int[] output = new int[n];

        while (max / exp > 0) {
            int[] count = new int[10];

            for (int i = 0; i < n; i++) {
                count[(nums[i] / exp) % 10]++;
            }

            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int digit = (nums[i] / exp) % 10;
                output[--count[digit]] = nums[i];
            }

            for (int i = 0; i < n; i++) {
                nums[i] = output[i];
            }

            exp *= 10;
        }

        int maxGap = 0;
        for (int i = 1; i < n; i++) {
            int gap = nums[i] - nums[i - 1];
            if (gap > maxGap) maxGap = gap;
        }

        return maxGap;
    }

    public static void main(String[] args) {
        int[] nums = {3, 6, 9, 1};

        int result = maximumGap(nums);
        System.out.println(result);
    }
}
