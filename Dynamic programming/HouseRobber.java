/*
LeetCode Problem: House Robber (Dynamic Programming)

QUESTION
You are given an integer array nums where nums[i] represents the amount of money
stored in the i-th house.

A robber cannot rob two adjacent houses because the security system will alert
the police.

Find the maximum amount of money the robber can steal without robbing two
adjacent houses.

Example:
Input: nums = [1,2,3,1]
Output: 4

Explanation:
Rob house 1 (1) and house 3 (3)
Total = 4

--------------------------------------------------

HOW THE ALGORITHM WORKS

At every house we have two choices:

1. Rob the current house
2. Skip the current house

If we rob house i:
    we cannot rob house (i-1)
    so total = nums[i] + dp[i-2]

If we skip house i:
    we take the previous maximum
    total = dp[i-1]

So the recurrence formula becomes:

dp[i] = max(
            dp[i-1],           // skip current house
            nums[i] + dp[i-2]  // rob current house
          )

--------------------------------------------------

STEP-BY-STEP TRACE

Example:
nums = [1,2,3,1]

Step 0:
dp[0] = 1

Step 1:
dp[1] = max(nums[0], nums[1])
dp[1] = max(1,2) = 2

Step 2:
dp[2] = max(dp[1], nums[2] + dp[0])
dp[2] = max(2, 3 + 1)
dp[2] = max(2,4)
dp[2] = 4

Step 3:
dp[3] = max(dp[2], nums[3] + dp[1])
dp[3] = max(4, 1 + 2)
dp[3] = max(4,3)
dp[3] = 4

DP Table

Index   Money   DP Value
0       1       1
1       2       2
2       3       4
3       1       4

Final Answer:
4

Optimal houses robbed:
House 0 and House 2
1 + 3 = 4

--------------------------------------------------

TIME COMPLEXITY
O(n)
We traverse the array once.

SPACE COMPLEXITY
O(n) for the dp array.
This can be optimized to O(1) by storing only the last two results.

--------------------------------------------------

WHY THIS IS DYNAMIC PROGRAMMING

The problem contains:
1. Optimal Substructure
2. Overlapping Subproblems

We reuse previously computed results instead of recalculating them.
*/

public class HouseRobber {

    public static int rob(int[] nums) {

        int n = nums.length;

        if(n == 1)
            return nums[0];

        int[] dp = new int[n];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for(int i = 2; i < n; i++) {

            dp[i] = Math.max(dp[i-1], nums[i] + dp[i-2]);

        }

        return dp[n-1];
    }

    public static void main(String[] args) {

        int[] nums = {1,2,3,1};

        int result = rob(nums);

        System.out.println("Maximum money robbed = " + result);
    }
}
