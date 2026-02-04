/**
 * Problem: Remove Duplicates from Sorted Array II (LeetCode #80)
 *
 * Given a sorted array nums, remove the duplicates **in-place** such that 
 * each element appear **at most twice** and return the new length.
 * 
 * Clarifications:
 * 1. The array is sorted in non-decreasing order.
 * 2. You must do this with O(1) extra space (modify the input array in-place).
 * 3. After removing duplicates, the first 'k' elements of nums should contain
 *    the final result. It doesn’t matter what values are beyond the first 'k'.
 *
 * Example:
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3,...]
 *
 * Approach: Two-Pointer Technique
 * - Use two pointers: 'i' (slow pointer) to keep track of the position to write
 *   the next allowed element, and 'j' (fast pointer) to scan through the array.
 * - For each nums[j], we check:
 *      - If i < 2, we always allow nums[j] (first two elements are always allowed)
 *      - Else if nums[j] != nums[i - 2], it means nums[j] is not a third duplicate,
 *        so we can write it to nums[i] and increment i.
 * - Return i as the new length of the array.
 *
 * Complexity:
 * - Time: O(n), since we traverse the array once
 * - Space: O(1), only a few variables used
 */

public class RemoveDuplicatesII {

    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n; // All elements allowed if array has length <= 2

        int i = 2; // slow pointer: position to write next allowed element

        // fast pointer scans from index 2
        for (int j = 2; j < n; j++) {
            // If current number is not the same as the number 2 positions before
            if (nums[j] != nums[i - 2]) {
                nums[i] = nums[j]; // write allowed element
                i++;
            }
        }

        return i; // new length of the array
    }

    // Optional main method to test
    public static void main(String[] args) {
        RemoveDuplicatesII solution = new RemoveDuplicatesII();
        int[] nums = {1,1,1,2,2,3};
        int k = solution.removeDuplicates(nums);

        System.out.println("New length: " + k);
        System.out.print("Modified array: ");
        for (int idx = 0; idx < k; idx++) {
            System.out.print(nums[idx] + " ");
        }
        // Output: New length: 5, Modified array: 1 1 2 2 3
    }
}
