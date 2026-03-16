/*
LeetCode Problem: Partition Equal Subset Sum

Optimized Dynamic Programming Solution

QUESTION
Given an integer array nums, return true if you can partition the array
into two subsets such that the sum of the elements in both subsets is equal.

Example:
Input: nums = [1,5,11,5]
Output: true

-----------------------------------------------------

HOW IT WORKS

1. Calculate total sum of the array.

2. If sum is odd → return false
   because two equal subsets cannot be formed.

3. Target sum = totalSum / 2

4. Use a boolean DP array:
   dp[i] = true → if subset with sum i exists.

5. Iterate numbers and update DP from right to left
   to avoid reusing the same number.

6. Early stop if dp[target] becomes true.

-----------------------------------------------------

TRACE EXAMPLE

nums = [1,5,11,5]

Total sum = 22
Target = 11

Initial DP:
dp[0] = true

Process 1:
dp[1] = true

Process 5:
dp[6] = true
dp[5] = true

Process 11:
dp[11] = true → TARGET FOUND

Return TRUE

-----------------------------------------------------

TIME COMPLEXITY
O(n * target)

SPACE COMPLEXITY
O(target)

-----------------------------------------------------
*/

public class PartitionEqualSubsetSum {

    public static boolean canPartition(int[] nums) {

        int sum = 0;

        for (int num : nums)
            sum += num;

        // If sum is odd, cannot split equally
        if ((sum & 1) == 1)
            return false;

        int target = sum / 2;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {

            for (int i = target; i >= num; i--) {

                if (dp[i - num])
                    dp[i] = true;

            }

            // Early stopping optimization
            if (dp[target])
                return true;
        }

        return dp[target];
    }

    public static void main(String[] args) {

        int[] nums = {1,5,11,5};

        boolean result = canPartition(nums);

        System.out.println("Can partition: " + result);
    }
}
