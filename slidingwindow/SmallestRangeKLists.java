import java.util.*;

/*
 * Problem: Smallest Range Covering Elements from K Lists (LeetCode 632)
 *
 * Given k sorted lists, find the smallest range [L, R] such that
 * at least one number from each list is included in the range.
 *
 * ------------------------------------------------------------
 * How It Works:
 * ------------------------------------------------------------
 * 1. Flatten all numbers into a list of (value, listIndex)
 * 2. Sort by value
 * 3. Use sliding window:
 *    - Expand right pointer to include all lists
 *    - Once all lists are included, shrink from left
 *    - Track minimum range
 *
 * ------------------------------------------------------------
 * Time Complexity:
 * ------------------------------------------------------------
 * O(N log N) due to sorting
 * N = total number of elements
 *
 * ------------------------------------------------------------
 * Space Complexity:
 * ------------------------------------------------------------
 * O(N)
 */

public class SmallestRangeKLists {

    public static int[] smallestRange(List<List<Integer>> nums) {
        List<int[]> list = new ArrayList<>();

        // Step 1: Flatten
        for (int i = 0; i < nums.size(); i++) {
            for (int num : nums.get(i)) {
                list.add(new int[]{num, i});
            }
        }

        // Step 2: Sort
        Collections.sort(list, (a, b) -> a[0] - b[0]);

        // Sliding window
        int left = 0;
        int count =
