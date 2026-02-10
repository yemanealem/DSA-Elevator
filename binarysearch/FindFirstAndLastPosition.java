import java.util.Arrays;

public class FindFirstAndLastPosition {

    /*
    LeetCode Problem 34: Find First and Last Position of Element in Sorted Array

    Description:
    Given an integer array nums sorted in ascending order, 
    find the starting and ending position of a given target value.

    If target is not found in the array, return [-1, -1].

    You must write an algorithm with O(log n) runtime complexity.

    Example 1:
    Input: nums = [5,7,7,8,8,10], target = 8
    Output: [3,4]

    Example 2:
    Input: nums = [5,7,7,8,8,10], target = 6
    Output: [-1,-1]

    Constraints:
    0 <= nums.length <= 10^5
    -10^9 <= nums[i] <= 10^9
    nums is a non-decreasing array.
    */

    /**
     * Function to find the first and last position of a target element in a sorted array.
     * @param nums: the sorted integer array
     * @param target: the element to search for
     * @return int[]: array containing first and last positions [first, last]
     * Time Complexity: O(log n) for each binary search → overall O(log n)
     * Space Complexity: O(1)
     */
    public static int[] searchRange(int[] nums, int target) {
        // Find first occurrence of target
        int first = findBound(nums, target, true);
        
        // If first occurrence not found, target does not exist in array
        if (first == -1) {
            return new int[]{-1, -1};
        }

        // Find last occurrence of target
        int last = findBound(nums, target, false);
        return new int[]{first, last};
    }

    /**
     * Helper function to find the boundary (first or last occurrence) of target
     * using binary search.
     * @param nums: sorted array
     * @param target: element to search
     * @param isFirst: true → find first occurrence, false → find last occurrence
     * @return int: index of boundary element, -1 if not found
     */
    private static int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1;
        int bound = -1;  // store the index of found target

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                bound = mid;  // update boundary
                if (isFirst) {
                    // Move left to find first occurrence
                    right = mid - 1;
                } else {
                    // Move right to find last occurrence
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                // Move right
                left = mid + 1;
            } else {
                // nums[mid] > target → Move left
                right = mid - 1;
            }
        }

        return bound;
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;

        // Find first and last positions
        int[] result = searchRange(nums, target);

        // Print result
        System.out.println("First and Last Position of " + target + ": " + Arrays.toString(result));
        // Expected Output: [3, 4]

        // Test a case where target is not found
        target = 6;
        result = searchRange(nums, target);
        System.out.println("First and Last Position of " + target + ": " + Arrays.toString(result));
        // Expected Output: [-1, -1]
    }
}

/*
Explanation:

1. The array is sorted → binary search can be used.
2. Two binary searches:
   - First occurrence: move 'right' pointer to mid-1 when target found.
   - Last occurrence: move 'left' pointer to mid+1 when target found.
3. If target not present → return [-1, -1].
4. Time Complexity:
   - Binary search takes O(log n)
   - Two searches → still O(log n)
5. Space Complexity: O(1) → no extra space used.
6. Example Trace:
   nums = [5, 7, 7, 8, 8, 10], target = 8
   - First occurrence → index 3
   - Last occurrence  → index 4
   Output → [3, 4]
*/
