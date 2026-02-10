
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
Explanation & Trace Example:

nums = [1, 2, 1, 3, 5, 6, 4]
Goal: Find any peak element.

Step 1:
left = 0, right = 6
mid = 0 + (6-0)/2 = 3
nums[mid] = 3, nums[mid+1] = 5
3 < 5 → move left = mid + 1 = 4

Step 2:
left = 4, right = 6
mid = 4 + (6-4)/2 = 5
nums[mid] = 6, nums[mid+1] = 4
6 > 4 → move right = mid = 5

Step 3:
left = 4, right = 5
mid = 4 + (5-4)/2 = 4
nums[mid] = 5, nums[mid+1] = 6
5 < 6 → move left = mid + 1 = 5

End:
left = 5, right = 5 → peak found at index 5
nums[5] = 6 is a peak because nums[5] > nums[4] and nums[5] > nums[6]

Summary Table:

| Iteration | left | right | mid | nums[mid] | nums[mid+1] | Action        |
|-----------|------|-------|-----|-----------|-------------|---------------|
| 1         | 0    | 6     | 3   | 3         | 5           | left = mid+1  |
| 2         | 4    | 6     | 5   | 6         | 4           | right = mid   |
| 3         | 4    | 5     | 4   | 5         | 6           | left = mid+1  |
| End       | 5    | 5     | -   | 6         | -           | Peak found    |

Time Complexity: O(log n)
Space Complexity: O(1)
*/
