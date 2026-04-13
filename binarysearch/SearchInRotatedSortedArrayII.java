public class SearchInRotatedSortedArrayII {

    /*
     * PROBLEM:
     * Given a rotated sorted array nums (possibly with duplicates),
     * return true if target exists in the array, otherwise false.
     *
     * Example:
     * nums = [2,5,6,0,0,1,2], target = 0  → true
     * nums = [2,5,6,0,0,1,2], target = 3  → false
     *
     * ------------------------------------------------------------
     * HOW IT WORKS (Modified Binary Search):
     *
     * Normally in rotated arrays (without duplicates), we can determine
     * which half is sorted by comparing nums[left] and nums[mid].
     *
     * BUT with duplicates, we may have:
     *      nums[left] == nums[mid] == nums[right]
     * → In this case, we cannot determine the sorted half.
     *
     * So we handle 3 cases:
     *
     * 1. If nums[mid] == target → return true
     *
     * 2. If duplicates block decision:
     *      nums[left] == nums[mid] == nums[right]
     *      → shrink search space:
     *         left++
     *         right--
     *
     * 3. Otherwise:
     *    - If left half is sorted:
     *         nums[left] <= nums[mid]
     *         → check if target is inside this range
     *
     *    - Else right half is sorted
     *         → check if target is inside that range
     *
     * ------------------------------------------------------------
     * TIME COMPLEXITY:
     * - Best/Average: O(log n)
     * - Worst case (many duplicates): O(n)
     *   (because we may shrink one-by-one)
     *
     * SPACE COMPLEXITY:
     * - O(1)
     */

    public static boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Case 1: found target
            if (nums[mid] == target) {
                return true;
            }

            // Case 2: duplicates (cannot decide sorted side)
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            }

            // Case 3: left half is sorted
            else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2,5,6,0,0,1,2};

        System.out.println(search(nums, 0)); 
        System.out.println(search(nums, 3)); 
    }
}
