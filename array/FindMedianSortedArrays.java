/*
===========================================================
LeetCode 4 - Median of Two Sorted Arrays
===========================================================

Question:
Given two sorted arrays nums1 and nums2 of size m and n,
return the median of the two sorted arrays.

The overall run time complexity must be O(log(m+n)).

-----------------------------------------------------------
HOW IT WORKS
-----------------------------------------------------------

1. Always binary search on smaller array.
2. Partition both arrays into left and right halves.
3. Ensure:
   max(left1, left2) <= min(right1, right2)

4. If total length is even:
   median = (maxLeft + minRight) / 2

5. If odd:
   median = maxLeft
-----------------------------------------------------------
*/

class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // Ensure nums1 is smaller
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int low = 0;
        int high = m;

        while (low <= high) {

            int partition1 = (low + high) / 2;
            int partition2 = (m + n + 1) / 2 - partition1;

            int maxLeft1 = (partition1 == 0) 
                    ? Integer.MIN_VALUE 
                    : nums1[partition1 - 1];

            int minRight1 = (partition1 == m) 
                    ? Integer.MAX_VALUE 
                    : nums1[partition1];

            int maxLeft2 = (partition2 == 0) 
                    ? Integer.MIN_VALUE 
                    : nums2[partition2 - 1];

            int minRight2 = (partition2 == n) 
                    ? Integer.MAX_VALUE 
                    : nums2[partition2];

            // Correct partition found
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {

                // Even length
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxLeft1, maxLeft2) +
                            Math.min(minRight1, minRight2)) / 2.0;
                } 
                // Odd length
                else {
                    return Math.max(maxLeft1, maxLeft2);
                }
            }
            // Move left
            else if (maxLeft1 > minRight2) {
                high = partition1 - 1;
            }
            // Move right
            else {
                low = partition1 + 1;
            }
        }

        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {

        MedianOfTwoSortedArrays solution =
                new MedianOfTwoSortedArrays();

        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};

        double median =
                solution.findMedianSortedArrays(nums1, nums2);

        System.out.println("Median: " + median);
    }
}
