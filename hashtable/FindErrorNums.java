import java.util.*;

class Solution {
    public int[] findErrorNums(int[] nums) {
        int i = 0;

        // Place each number in its correct position
        while (i < nums.length) {
            int correctIndex = nums[i] - 1;

            if (nums[i] != nums[correctIndex]) {
                // swap
                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = temp;
            } else {
                i++;
            }
        }

        // Find duplicate and missing
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j + 1) {
                return new int[]{nums[j], j + 1};
            }
        }

        return new int[]{-1, -1}; 
    }
}
