
public class CountMajoritySubarrays {

    /*
    QUESTION:
    ----------
    Given an integer array nums and a target element,
    count the number of subarrays where target is the majority element.

    APPROACH:
    ----------
    1. Transform nums:
       +1 if nums[i] == target, -1 otherwise.
    2. Compute prefix sum array.
    3. Count subarrays with positive sum (prefix[j] - prefix[i] > 0)
       using merge sort to efficiently count cross-subarray pairs.
    */

    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        long[] prefix = new long[n + 1];

        // Build prefix sum (+1 / -1)
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (nums[i] == target ? 1 : -1);
        }

        long[] temp = new long[n + 1]; // reuse temp array
        return (int) mergeSort(prefix, 0, n, temp);
    }

    private long mergeSort(long[] arr, int left, int right, long[] temp) {
        if (left >= right) return 0;

        int mid = left + (right - left) / 2;
        long count = 0;

        count += mergeSort(arr, left, mid, temp);
        count += mergeSort(arr, mid + 1, right, temp);
        count += merge(arr, left, mid, right, temp); // always merge

        return count;
    }

    private long merge(long[] arr, int left, int mid, int right, long[] temp) {
        long count = 0;

        // Copy range into temp
        System.arraycopy(arr, left, temp, left, right - left + 1);

        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            if (temp[i] < temp[j]) {
                count += (right - j + 1); // count valid pairs
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }

        while (i <= mid) arr[k++] = temp[i++];
        while (j <= right) arr[k++] = temp[j++];

        return count;
    }

    // Local test
    public static void main(String[] args) {
        CountMajoritySubarrays solver = new CountMajoritySubarrays();

        int[] nums = {2, 2, 1, 2};
        int target = 2;

        System.out.println("Count = " + solver.countMajoritySubarrays(nums, target));
        // Output: 7
    }
}
