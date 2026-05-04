/*
------------------------------------------------------------
Problem: Count Good Triplets in an Array (LeetCode 2179)

We are given two permutations nums1 and nums2.
We need to count triplets (i, j, k) such that:
- i < j < k
- Relative order of nums1 matches nums2

------------------------------------------------------------
How it works:

1. Map each value in nums2 → its index position.
2. Convert nums1 into an index array based on nums2.
3. For each element:
   - leftSmaller[i]  = number of elements before i smaller than it
   - rightGreater[i] = number of elements after i greater than it
4. Each index contributes:
      leftSmaller[i] * rightGreater[i]

We compute both using Merge Sort (divide & conquer counting).

------------------------------------------------------------
Time Complexity: O(n log n)
Space Complexity: O(n)
------------------------------------------------------------
*/

public class goodTriplets {

    static int[] temp;

    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;

        // Map nums2 value -> index (O(1) access)
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[nums2[i]] = i;
        }

        // Convert nums1 into index array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = pos[nums1[i]];
        }

        int[] left = new int[n];
        int[] right = new int[n];
        temp = new int[n];

        mergeLeft(arr.clone(), left, 0, n - 1);
        mergeRight(arr, right, 0, n - 1);

        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (long) left[i] * right[i];
        }

        return ans;
    }

    // Count smaller elements on the left
    private void mergeLeft(int[] arr, int[] left, int l, int r) {
        if (l >= r) return;

        int m = (l + r) / 2;
        mergeLeft(arr, left, l, m);
        mergeLeft(arr, left, m + 1, r);

        int i = l, j = m + 1, k = l;
        int rightCount = 0;

        while (i <= m && j <= r) {
            if (arr[i] < arr[j]) {
                left[arr[i]] += rightCount;
                temp[k++] = arr[i++];
            } else {
                rightCount++;
                temp[k++] = arr[j++];
            }
        }

        while (i <= m) {
            left[arr[i]] += rightCount;
            temp[k++] = arr[i++];
        }

        while (j <= r) {
            temp[k++] = arr[j++];
        }

        for (int x = l; x <= r; x++) {
            arr[x] = temp[x];
        }
    }

    // Count greater elements on the right
    private void mergeRight(int[] arr, int[] right, int l, int r) {
        if (l >= r) return;

        int m = (l + r) / 2;
        mergeRight(arr, right, l, m);
        mergeRight(arr, right, m + 1, r);

        int i = l, j = m + 1, k = l;

        while (i <= m && j <= r) {
            if (arr[i] > arr[j]) {
                right[arr[i]] += (r - j + 1);
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= m) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];

        for (int x = l; x <= r; x++) {
            arr[x] = temp[x];
        }
    }
}
