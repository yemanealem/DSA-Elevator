public class FindMinimumInRotatedSortedArray {

    /*
    ---------------------------------------------------------
    LeetCode 153 - Find Minimum in Rotated Sorted Array
    ---------------------------------------------------------

    Approach: Binary Search

    We compare middle element with rightmost element.

    If nums[mid] > nums[right]:
        Minimum is in right half.
        Move left pointer.

    Else:
        Minimum is in left half (including mid).
        Move right pointer.

    Time Complexity: O(log n)
    Space Complexity: O(1)
    ---------------------------------------------------------
    */

    public static int findMin(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            // Minimum is in right half
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            }
            // Minimum is in left half (including mid)
            else {
                right = mid;
            }
        }

        // left == right → minimum element
        return nums[left];
    }

    // Main method
    public static void main(String[] args) {

        int[] nums = {4,5,6,7,0,1,2};

        int result = findMin(nums);

        System.out.println("Minimum Element: " + result);
    }
}
