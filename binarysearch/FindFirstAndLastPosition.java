import java.util.Arrays;

public class FindFirstAndLastPosition {

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
6. Example:
   nums = [5, 7, 7, 8, 8, 10], target = 8
   - First occurrence → index 3
   - Last occurrence  → index 4
   Output → [3, 4]
*/
