package mergesort;

public class CountMajoritySubarrays {
     int n = nums.length;
        long[] prefix = new long[n + 1];

        // Build prefix sum array: +1 if nums[i]==target else -1
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (nums[i] == target ? 1 : -1);
        }

        // Use merge sort to count the number of subarrays with positive sum
        return (int) mergeSort(prefix, 0, n);
    }

    private long mergeSort(long[] arr, int left, int right) {
        if (left >= right) return 0;

        int mid = left + (right - left) / 2;
        long count = 0;

        count += mergeSort(arr, left, mid);
        count += mergeSort(arr, mid + 1, right);
        count += merge(arr, left, mid, right);

        return count;
    }

    private long merge(long[] arr, int left, int mid, int right) {
        long count = 0;
        long[] temp = new long[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        // Count pairs: arr[i] < arr[j]
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                count += (right - j + 1); // all remaining in right half
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, left, temp.length);
        return count;
}
