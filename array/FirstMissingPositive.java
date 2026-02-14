/*
LeetCode 41 - First Missing Positive

Problem:
Given an unsorted integer array nums,
return the smallest missing positive integer.

Constraints:
- Time Complexity: O(n)
- Space Complexity: O(1)

Idea:
Place each number x in index (x - 1).
Then scan the array to find the first index
where nums[i] != i + 1.
*/

import java.util.Arrays;

class FirstMissingPositive {

    // Core method
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Step 1: Place numbers in correct positions
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n 
                    && nums[nums[i] - 1] != nums[i]) {
                
                int correctIndex = nums[i] - 1;

                // swap
                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = temp;
            }
        }

        // Step 2: Find first missing positive
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }

    // Main method for testing
    public static void main(String[] args) {

        FirstMissingPositive solution = new FirstMissingPositive();

        int[] arr1 = {1, 2, 0};
        int[] arr2 = {3, 4, -1, 1};
        int[] arr3 = {7, 8, 9, 11, 12};

        System.out.println("Input: " + Arrays.toString(arr1));
        System.out.println("Missing Positive: " 
                + solution.firstMissingPositive(arr1));

        System.out.println("-----------------------------");

        System.out.println("Input: " + Arrays.toString(arr2));
        System.out.println("Missing Positive: " 
                + solution.firstMissingPositive(arr2));

        System.out.println("-----------------------------");

        System.out.println("Input: " + Arrays.toString(arr3));
        System.out.println("Missing Positive: " 
                + solution.firstMissingPositive(arr3));
    }
}
