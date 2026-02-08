package mergesort;

public class CountMajoritySubarrays {
     int n = nums.length;
        long[] prefix = new long[n + 1];

        // Build prefix sum (+1 / -1)
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (nums[i] == target ? 1 : -1);
        }

        long[] temp = new long[n + 1]; // reuse across recursion

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

        // Copy only the needed range
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
