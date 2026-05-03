/*
LeetCode: Wiggle Sort II

Question:
Rearrange the array nums such that:
nums[0] < nums[1] > nums[2] < nums[3] > nums[4] ...

How it works:
1. Clone and sort the array.
2. Split it into two halves:
   - Smaller half
   - Larger half
3. Fill the original array:
   - Even indices (0,2,4...) with smaller half in reverse
   - Odd indices (1,3,5...) with larger half in reverse
This avoids duplicates breaking the strict < > pattern.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

import java.util.Arrays;

public class WiggleSortII {

    public static void wiggleSort(int[] nums) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int left = (n + 1) / 2 - 1;
        int right = n - 1;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                nums[i] = sorted[left--];
            } else {
                nums[i] = sorted[right--];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 1, 1, 6, 4};

        wiggleSort(nums);

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
