
/*
================================================================================
LeetCode 1863 – Sum of All Subset XOR Totals

QUESTION:
Given an integer array nums, return the sum of XOR totals of every subset.

The XOR total of a subset is defined as the bitwise XOR of all its elements.
The XOR total of the empty subset is 0.

Example:
Input: nums = [1, 3]
Subsets:
[]      → XOR = 0
[1]     → XOR = 1
[3]     → XOR = 3
[1, 3]  → XOR = 1 ^ 3 = 2

Output: 0 + 1 + 3 + 2 = 6


APPROACH (BACKTRACKING):
This is a classic subset-generation problem.

For each element, we have TWO choices:
1) Include the element in the subset
2) Exclude the element from the subset

We maintain a running XOR value (`currentXor`) instead of storing subsets.
When we reach the end of the array, we add the XOR total to the answer.

This avoids extra memory usage and keeps the solution efficient.


HOW IT WORKS – STEP-BY-STEP TRACE (nums = [1, 3]):

Start:
index = 0, currentXor = 0

Choice at index 0 (value = 1):
--------------------------------
1) Include 1:
   currentXor = 0 ^ 1 = 1

   Choice at index 1 (value = 3):
   - Include 3 → currentXor = 1 ^ 3 = 2 → add 2
   - Exclude 3 → currentXor = 1        → add 1

2) Exclude 1:
   currentXor = 0

   Choice at index 1 (value = 3):
   - Include 3 → currentXor = 3 → add 3
   - Exclude 3 → currentXor = 0 → add 0

Final Sum = 2 + 1 + 3 + 0 = 6


WHY BACKTRACKING WORKS HERE:
- Every subset is explored exactly once
- XOR is reversible (a ^ b ^ b = a), so no explicit undo is needed
- We only carry an integer instead of building subset lists


TIME & SPACE COMPLEXITY:
Time:  O(2^N)  → all subsets
Space: O(N)    → recursion stack

================================================================================
*/

public class SumOfSubsetXORTotals {

    static class Solution {
        int totalSum = 0;

        public int subsetXORSum(int[] nums) {
            backtrack(nums, 0, 0);
            return totalSum;
        }

        private void backtrack(int[] nums, int index, int currentXor) {
            // when all elements are considered
            if (index == nums.length) {
                totalSum += currentXor;
                return;
            }

            // include nums[index]
            backtrack(nums, index + 1, currentXor ^ nums[index]);

            // exclude nums[index]
            backtrack(nums, index + 1, currentXor);
        }
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {

        int[] nums = {1, 3};

        Solution solution = new Solution();
        int result = solution.subsetXORSum(nums);

        System.out.println("Sum of all subset XOR totals: " + result);
    }
}
