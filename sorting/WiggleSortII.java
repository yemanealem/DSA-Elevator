/*
LeetCode: Wiggle Sort II

Question:
Rearrange the array nums such that:
nums[0] < nums[1] > nums[2] < nums[3] > nums[4] ...

How it works:
1. Sort a copy of the array.
2. Split into two halves.
3. Fill:
   - Even indices with smaller half (reverse)
   - Odd indices with larger half (reverse)

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

import java.util.Arrays;

public class WiggleSortII {

    public static void wiggleSort(int[] nums) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int left = (n - 1) >> 1; 
        int right = n - 1;

        for (int i = 0; i < n; i++) {
            nums[i] = ( (i & 1) == 0 ) ? sorted[left--] : sorted[right--];
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
