import java.util.*;

public class SetMismatchDemo {

    /*
    ============================= SET MISMATCH PROBLEM =============================

    Problem:
    You are given an integer array nums of size n containing numbers from 1 to n.

    Exactly:
    - One number appears twice (duplicate)
    - One number is missing

    Return:
        [duplicate, missing]

    ------------------------------------------------------------------------------
    
    Example:
    Input:  nums = [1,2,2,4]
    Output: [2,3]

    Explanation:
    - 2 appears twice → duplicate
    - 3 is missing

    ------------------------------------------------------------------------------
    
    How It Works (Cycle Sort / In-place Placement):

    1. Each number should be at index = (num - 1)

    2. Iterate through the array:
        - If nums[i] is not in the correct position,
          swap it with the value at its correct index

    3. After placement:
        - The duplicate will remain in the wrong position
        - The missing number is where nums[i] != i + 1

    4. Return:
        [nums[i], i + 1]

    ------------------------------------------------------------------------------
    
    Key Idea:
    By placing each number into its correct index,
    we directly identify the duplicate and missing values.

    ------------------------------------------------------------------------------
    
    Time Complexity:  O(n)
    Space Complexity: O(1)

    ===============================================================================
    */

    public static int[] findErrorNums(int[] nums) {
        int i = 0;
        int n = nums.length;

        // Place each number in the correct index
        while (i < n) {
            int correctIndex = nums[i] - 1;

            if (nums[i] != nums[correctIndex]) {
                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = temp;
            } else {
                i++;
            }
        }

        // Find duplicate and missing
        for (int j = 0; j < n; j++) {
            if (nums[j] != j + 1) {
                return new int[]{nums[j], j + 1};
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 4};

        int[] result = findErrorNums(nums);

        System.out.println("Duplicate: " + result[0]);
        System.out.println("Missing: " + result[1]);
    }
}
