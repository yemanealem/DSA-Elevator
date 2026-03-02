/**
 * LeetCode: Product of Array Except Self
 *
 * Given an integer array nums, return an array answer such that:
 * answer[i] is equal to the product of all the elements of nums except nums[i].
 *
 * Constraints:
 * - Do NOT use division.
 * - Must run in O(n) time.
 *
 * Approach:
 * 1. First pass: compute prefix (left) products.
 * 2. Second pass: multiply with suffix (right) products.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1) (excluding output array)
 */

public class ProductOfArrayExceptSelf {

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Step 1: Store prefix products
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // Step 2: Multiply with suffix products
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= right;
            right *= nums[i];
        }

        return result;
    }

    // Main method to test
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] result = productExceptSelf(nums);

        System.out.print("Output: ");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
