/*
------------------------------------------------------------
Problem: Count Good Triplets in an Array (LeetCode 2179)

We are given two permutations nums1 and nums2.

A "good triplet" (i, j, k) satisfies:
- i < j < k
- Relative ordering of nums1 matches nums2

------------------------------------------------------------
Key Idea:

1. Map each value in nums2 → its index position.
2. Transform nums1 into an index array "arr".
3. For each index j:
   - left[j]  = number of elements before j smaller than arr[j]
   - right[j] = number of elements after j greater than arr[j]
4. Answer = sum(left[j] * right[j])

We compute both using Fenwick Tree (BIT).

------------------------------------------------------------
Time Complexity: O(n log n)
Space Complexity: O(n)
------------------------------------------------------------
*/

import java.util.*;

class Solution {

    // Fenwick Tree (BIT)
    static class BIT {
        int[] tree;
        int n;

        BIT(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        void update(int i, int val) {
            while (i <= n) {
                tree[i] += val;
                i += i & -i;
            }
        }

        int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }
    }

    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;

        // Step 1: map value -> index in nums2
        Map<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            pos.put(nums2[i], i + 1); // 1-based indexing
        }

        // Step 2: transform nums1
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = pos.get(nums1[i]);
        }

        int[] left = new int[n];
        int[] right = new int[n];

        BIT bitLeft = new BIT(n);
        BIT bitRight = new BIT(n);

        // Step 3: left smaller counts
        for (int i = 0; i < n; i++) {
            left[i] = bitLeft.query(arr[i] - 1);
            bitLeft.update(arr[i], 1);
        }

        // Step 4: right greater counts
        for (int i = n - 1; i >= 0; i--) {
            right[i] = bitRight.query(n) - bitRight.query(arr[i]);
            bitRight.update(arr[i], 1);
        }

        // Step 5: compute result
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (long) left[i] * right[i];
        }

        return ans;
    }
}
