/*
===========================================================
LeetCode 41 - First Missing Positive
===========================================================

Question:
Given an unsorted integer array nums,
return the smallest missing positive integer.

You must:
- Run in O(n) time
- Use O(1) extra space

Example 1:
Input:  [1,2,0]
Output: 3

Example 2:
Input:  [3,4,-1,1]
Output: 2

Example 3:
Input:  [7,8,9,11,12]
Output: 1


-----------------------------------------------------------
HOW IT WORKS (Core Idea)
-----------------------------------------------------------

Observation:
The answer must be between 1 and n+1
(where n = array length).

We place each number x in its correct index:
Number x should be at index (x - 1).

After rearranging:
The first index i where nums[i] != i + 1
is our missing number.

If all positions are correct,
return n + 1.


-----------------------------------------------------------
TRACE EXAMPLE
-----------------------------------------------------------

Input:
[3,4,-1,1]

Length = 4

Step 1: Rearrange numbers

i = 0
nums[0] = 3
Correct index = 3 - 1 = 2
Swap → [-1,4,3,1]

nums[0] = -1 (ignore because <= 0)

i = 1
nums[1] = 4
Correct index = 4 - 1 = 3
Swap → [-1,1,3,4]

nums[1] = 1
Correct index = 1 - 1 = 0
Swap → [1,-1,3,4]

nums[1] = -1 (ignore)

i = 2 → already correct (3)
i = 3 → already correct (4)

Final array:
[1,-1,3,4]

Step 2: Find first mismatch

Index 0 → 1 (correct)
Index 1 → -1 (should be 2)

Answer = 2

-----------------------------------------------------------
Time Complexity: O(n)
Space Complexity: O(1)
-----------------------------------------------------------
*/

import java.util.Arrays;

class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Step 1: Place each number in its correct index
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n
                    && nums[nums[i] - 1] != nums[i]) {

                int correctIndex = nums[i] - 1;

                // swap nums[i] and nums[correctIndex]
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

    public static void main(String[] args) {

        FirstMissingPositive solution = new FirstMissingPositive();

        int[] arr = {3, 4, -1, 1};

        System.out.println("Original Array: " + Arrays.toString(arr));
        int result = solution.firstMissingPositive(arr);
        System.out.println("Smallest Missing Positive: " + result);
    }
}
