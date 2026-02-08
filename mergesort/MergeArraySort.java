import java.util.Arrays;

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

        // 🔥 Enhancement: skip merge if already sorted
        if (nums[mid] <= nums[mid + 1]) return;

        merge(nums, left, mid, right, temp);
    }

    private void merge(int[] nums, int left, int mid, int right, int[] temp) {
        // Copy only the needed range
        System.arraycopy(nums, left, temp, left, right - left + 1);

        int i = left;      // left half pointer
        int j = mid + 1;   // right half pointer

        for (int k = left; k <= right; k++) {
            if (i > mid) {
                nums[k] = temp[j++];
            } else if (j > right) {
                nums[k] = temp[i++];
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i++];
            } else {
                nums[k] = temp[j++];
            }
        }
    }

    // 🔎 Test locally
    public static void main(String[] args) {
        MergeArraySort sorter = new MergeArraySort();

        int[] nums = {5, 2, 3, 1, -4, 7, 0};
        System.out.println("Before: " + Arrays.toString(nums));

        sorter.sortArray(nums);

        System.out.println("After:  " + Arrays.toString(nums));
    }
}
