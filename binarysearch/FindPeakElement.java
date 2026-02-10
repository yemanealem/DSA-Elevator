
public class FindPeakElement {

    /*
    LeetCode Problem 162: Find Peak Element

    A peak element is an element that is strictly greater than its neighbors.

    Given an integer array nums, find a peak element, and return its index. 
    If the array contains multiple peaks, return the index to any of the peaks.

    You may imagine that nums[-1] = nums[n] = -∞ (negative infinity).

    Example 1:
    Input: nums = [1,2,3,1]
    Output: 2
    Explanation: 3 is a peak element and your function should return the index 2.

    Example 2:
    Input: nums = [1,2,1,3,5,6,4]
    Output: 1 or 5
    Explanation: Your function can return either index 1 where the peak is 2, 
                 or index 5 where the peak is 6.

    Constraints:
    1 <= nums.length <= 1000
    -2^31 <= nums[i] <= 2^31 - 1
    nums[i] != nums[i + 1] for all valid i

    Follow-up: Could you implement a solution with logarithmic complexity?
    */

    /**
     * Function to find a peak element in the array using binary search.
     * @param nums: integer array
     * @return int: index of any peak element
     * Time Complexity: O(log n) → binary search
     * Space Complexity: O(1)
     */
    public static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Compare middle element with its right neighbor
            if (nums[mid] < nums[mid + 1]) {
                // Peak must be on the right side
                left = mid + 1;
            } else {
                // Peak is on the left side or at mid
                right = mid;
            }
        }

        // left == right is the peak index
        return left;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Peak index: " + findPeakElement(nums1)); // Output: 2

        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        System.out.println("Peak index: " + findPeakElement(nums2)); // Output: 1 or 5
    }
}

/*
Explanation:

1. Use binary search to find a peak element:
   - Compare nums[mid] with nums[mid+1]
   - If nums[mid] < nums[mid+1], move to the right half → peak is there
   - Else, move to the left half → peak is at mid or left
2. Repeat until left == right → this is the peak index
3. Time Complexity: O(log n) → binary search
4. Space Complexity: O(1) → no extra space used
5. Works because nums[-1] and nums[n] are treated as -∞
6. Returns any peak (multiple peaks are allowed)
*/
