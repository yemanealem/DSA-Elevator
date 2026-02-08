import java.util.Arrays;

/*
QUESTION (LeetCode 912 – Sort an Array):
--------------------------------------
Given an array of integers nums, sort the array in ascending order and return it.
You must solve the problem without using built-in sort functions.

APPROACH (Optimized Merge Sort):
--------------------------------
- Use divide and conquer:
  1. Recursively split the array into two halves.
  2. Sort each half.
  3. Merge the two sorted halves.
- Optimization: Skip the merge step if the two halves are already sorted
  (nums[mid] <= nums[mid + 1]) to avoid unnecessary copying.

TIME & SPACE COMPLEXITY:
------------------------
- Time: O(n log n) in the worst case
- Space: O(n) auxiliary space (temp array)

TRACE (Example):
----------------
Input: [5, 2, 3, 1]

Step 1: Split
[5, 2] | [3, 1]

Step 2: Split further
[5] [2] [3] [1]

Step 3: Merge sorted subarrays
[5] + [2] → [2, 5]
[3] + [1] → [1, 3]

Step 4: Final merge
[2, 5] + [1, 3] → [1, 2, 3, 5]

Final Output: [1, 2, 3, 5]
*/

public class MergeArraySort {

    public int[] sortArray(int[] nums) {
        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, temp);
        return nums;
    }

    private void mergeSort(int[] nums, int left, int right, int[] temp) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;

        mergeSort(nums, left, mid, temp);
        mergeSort(nums, mid + 1, right, temp);

        // Optimization: skip merge if already sorted
        if (nums[mid] <= nums[mid + 1]) return;

        merge(nums, left, mid, right, temp);
    }

    private void merge(int[] nums, int left, int mid, int right, int[] temp) {
        System.arraycopy(nums, left, temp, left, right - left + 1);

        int i = left;
        int j = mid + 1;

        for (int k = left; k <= right; k++) {
            if (i > mid) nums[k] = temp[j++];
            else if (j > right) nums[k] = temp[i++];
            else if (temp[i] <= temp[j]) nums[k] = temp[i++];
            else nums[k] = temp[j++];
        }
    }

    // Local test
    public static void main(String[] args) {
        MergeArraySort sorter = new MergeArraySort();

        int[] nums = {5, 2, 3, 1};
        System.out.println("Before: " + Arrays.toString(nums));

        sorter.sortArray(nums);

        System.out.println("After:  " + Arrays.toString(nums));
    }
}
