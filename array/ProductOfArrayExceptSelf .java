/**
 * LeetCode: Product of Array Except Self
 *
 * Given an integer array nums, return an array answer such that:
 * answer[i] is equal to the product of all the elements of nums except nums[i].
 *
 * Constraints:
 * - No division
 * - O(n) time
 * - O(1) extra space (excluding output)
 *
 * Approach:
 * 1. First pass: store prefix product directly in result[]
 * 2. Second pass: multiply with suffix product using one variable
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

public class ProductExceptSelf {

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int prefix = 1;
        for (int i = 0; i < n; i++) {
            result[i] = prefix;
            prefix *= nums[i];
        }

        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffix;
            suffix *= nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] ans = productExceptSelf(nums);

        for (int num : ans) {
            System.out.print(num + " ");
        }
    }
}
