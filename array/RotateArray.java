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

-----------------------------------------------------------
DETAILED TRACE EXAMPLE
-----------------------------------------------------------

Input:
nums = [1,2,3,4,5,6,7]
k = 3

n = 7

Step 0:
k = k % n
k = 3 % 7 = 3

-----------------------------------------------------------
STEP 1: Reverse entire array
-----------------------------------------------------------

Before:
[1,2,3,4,5,6,7]

After reversing whole array:
[7,6,5,4,3,2,1]

-----------------------------------------------------------
STEP 2: Reverse first k elements (0 to k-1)
-----------------------------------------------------------

Reverse first 3 elements:
[7,6,5]

After reversing:
[5,6,7,4,3,2,1]

-----------------------------------------------------------
STEP 3: Reverse remaining elements (k to n-1)
-----------------------------------------------------------

Reverse from index 3 to 6:
[4,3,2,1]

After reversing:
[5,6,7,1,2,3,4]

-----------------------------------------------------------
FINAL RESULT:
[5,6,7,1,2,3,4]

Time Complexity: O(n)
Space Complexity: O(1)
-----------------------------------------------------------
*/

import java.util.Arrays;

class RotateArray {

    public void rotate(int[] nums, int k) {

        int n = nums.length;

        // handle k greater than array length
        k = k % n;

        // Step 1
        reverse(nums, 0, n - 1);

        // Step 2
        reverse(nums, 0, k - 1);

        // Step 3
        reverse(nums, k, n - 1);
    }

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
