/*
 * LeetCode - Find Minimum in Rotated Sorted Array II
 *
 * QUESTION:
 * You are given a sorted array that has been rotated at an unknown pivot.
 * The array may contain duplicates.
 * Your task is to find the minimum element in the array.
 *
 * Example:
 * Input: [2,2,2,0,1]
 * Output: 0
 *
 *
 * HOW IT WORKS (Binary Search with Duplicates):
 *
 * We use a modified binary search approach.
 *
 * At each step:
 *   - Compare nums[mid] with nums[right]
 *
 * CASE 1: nums[mid] > nums[right]
 *   → Minimum is in the right half
 *   → Move left = mid + 1
 *
 * CASE 2: nums[mid] < nums[right]
 *   → Minimum is in the left half (including mid)
 *   → Move right = mid
 *
 * CASE 3: nums[mid] == nums[right]
 *   → We cannot decide the side due to duplicates
 *   → Reduce search space by doing right--
 *
 * WHY right--?
 *   Because nums[right] is a duplicate and does not help identify the minimum.
 *
 * Time Complexity:
 *   Best/Average: O(log n)
 *   Worst Case (many duplicates): O(n)
 *
 * Key Insight:
 *   Duplicates break strict ordering, so sometimes we fall back to shrinking the range linearly.
 */

public class FindMinRotatedSortedArrayII {

    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                // Minimum is in right half
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                // Minimum is in left half including mid
                right = mid;
            } else {
                // nums[mid] == nums[right], can't decide
                right--;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 2, 0, 1};
        System.out.println("Minimum element: " + findMin(nums));
    }
}
