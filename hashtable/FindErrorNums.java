import java.util.*;

class Solution {
    public int[] findErrorNums(int[] nums) {
        int i = 0;
        int n = nums.length;

        // Cycle placement (in-place)
        while (i < n) {
            int correctIndex = nums[i] - 1;

            // Only swap if needed and avoid redundant swaps
            if (nums[i] != nums[correctIndex]) {
                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = temp;
            } else {
                i++;
            }
        }

        // Find mismatch
        for (int j = 0; j < n; j++) {
            if (nums[j] != j + 1) {
                return new int[]{nums[j], j + 1};
            }
        }

        return new int[]{-1, -1};
    }
}
