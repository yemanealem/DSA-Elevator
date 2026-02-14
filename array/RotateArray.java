/*
===========================================================
LeetCode 189 - Rotate Array
===========================================================

Question:
Given an integer array nums, rotate the array 
to the right by k steps.

You must:
- Do it in-place
- Use O(1) extra space

Example:
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]

-----------------------------------------------------------
HOW IT WORKS (Reverse Trick)
-----------------------------------------------------------

If we rotate right by k:

1) Reverse whole array
2) Reverse first k elements
3) Reverse remaining elements

Example Trace:

Original:
[1,2,3,4,5,6,7]
k = 3

Step 1:
Reverse whole array:
[7,6,5,4,3,2,1]

Step 2:
Reverse first 3:
[5,6,7,4,3,2,1]

Step 3:
Reverse rest:
[5,6,7,1,2,3,4]

-----------------------------------------------------------
Time Complexity: O(n)
Space Complexity: O(1)
-----------------------------------------------------------
*/

import java.util.Arrays;

class RotateArray {

    public void rotate(int[] nums, int k) {

        int n = nums.length;

        // Important: handle k > n
        k = k % n;

        // Step 1: Reverse entire array
        reverse(nums, 0, n - 1);

        // Step 2: Reverse first k elements
        reverse(nums, 0, k - 1);

        // Step 3: Reverse remaining elements
        reverse(nums, k, n - 1);
    }

    // Helper method to reverse part of array
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {

        RotateArray solution = new RotateArray();

        int[] arr = {1,2,3,4,5,6,7};
        int k = 3;

        System.out.println("Original: " + Arrays.toString(arr));

        solution.rotate(arr, k);

        System.out.println("Rotated:  " + Arrays.toString(arr));
    }
}
