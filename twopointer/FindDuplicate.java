/**
 * Problem: Find the Duplicate Number (LeetCode #287)
 *
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * there is exactly one duplicate number. Find the duplicate without modifying the array
 * and using O(1) extra space.
 *
 * Approach: Two-Pointer (Floyd's Tortoise and Hare / Cycle Detection)
 * - Treat nums as a linked list where nums[i] is the "next" pointer.
 * - The duplicate number creates a cycle.
 * - Use slow and fast pointers to find the cycle start.
 *
 * Complexity:
 * - Time: O(n)
 * - Space: O(1)
 */
public class LongestPalindrome { // Reusing class name if needed
    public int findDuplicate(int[] nums) {
        // Phase 1: Find intersection point
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];           // move one step
            fast = nums[nums[fast]];     // move two steps
        } while (slow != fast);

        // Phase 2: Find entrance to the cycle (duplicate number)
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow; // duplicate number
    }

    // Optional test
    public static void main(String[] args) {
        LongestPalindrome solution = new LongestPalindrome();
        int[] nums1 = {1, 3, 4, 2, 2};
        int[] nums2 = {3, 1, 3, 4, 2};
        System.out.println(solution.findDuplicate(nums1)); // Output: 2
        System.out.println(solution.findDuplicate(nums2)); // Output: 3
    }
}
