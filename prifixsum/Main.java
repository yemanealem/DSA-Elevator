/*
 * QUESTION:
 * Range Sum Query - Immutable
 * ---------------------------
 * Given an integer array nums, handle multiple queries of the following type:
 * sumRange(left, right) -> returns the sum of elements from index left to right (inclusive).
 *
 * The array is immutable (does not change after initialization).
 *
 * HOW IT WORKS (Prefix Sum Approach):
 * ---------------------------
 * We precompute a prefix sum array where:
 * prefixSum[i] = sum of elements from index 0 to i-1.
 *
 * Example:
 * nums = [-2, 0, 3, -5, 2, -1]
 * prefixSum = [0, -2, -2, 1, -4, -2, -3]
 *
 * To get sum from left to right:
 * sum = prefixSum[right + 1] - prefixSum[left]
 *
 * Example:
 * sumRange(0, 2) = prefixSum[3] - prefixSum[0] = 1 - 0 = 1
 *
 * RUNNING TIME:
 * ---------------------------
 * - Constructor (prefix sum creation): O(n)
 * - sumRange query: O(1)
 * - Space Complexity: O(n)
 */

class NumArray {

    private int[] prefixSum;

    public NumArray(int[] nums) {
        int n = nums.length;
        prefixSum = new int[n + 1];
        prefixSum[0] = 0;

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return prefixSum[right + 1] - prefixSum[left];
    }
}

public class Main {
    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};

        NumArray obj = new NumArray(nums);

        System.out.println(obj.sumRange(0, 2)); // Output: 1
        System.out.println(obj.sumRange(2, 5)); // Output: -1
        System.out.println(obj.sumRange(0, 5)); // Output: -3
    }
}
