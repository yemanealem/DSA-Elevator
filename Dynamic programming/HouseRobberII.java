/*
LeetCode Problem: House Robber II (Dynamic Programming)

QUESTION
You are given an integer array nums where nums[i] represents the amount of
money in the i-th house.

A robber cannot rob two adjacent houses.

However, in this problem the houses are arranged in a CIRCLE.
This means the first house and the last house are also adjacent.

Find the maximum amount of money the robber can steal without robbing
two adjacent houses.

Example:
Input: nums = [2,3,2]
Output: 3

Explanation:
You cannot rob house 0 and house 2 because they are adjacent (circular).
The best choice is rob house 1 → money = 3

------------------------------------------------

KEY IDEA

Because the houses form a circle:

You cannot rob:
House 0 and House n-1 together

So we break the problem into TWO linear robberies.

Case 1:
Rob houses from index 0 → n-2
(skip the last house)

Case 2:
Rob houses from index 1 → n-1
(skip the first house)

Then take the maximum result.

max(rob(0 → n-2), rob(1 → n-1))

Each case becomes the normal House Robber problem.

------------------------------------------------

STEP-BY-STEP TRACE

Example:
nums = [2,3,2]

Case 1 (exclude last house)
houses = [2,3]

dp[0] = 2
dp[1] = max(2,3) = 3

result = 3

Case 2 (exclude first house)
houses = [3,2]

dp[0] = 3
dp[1] = max(3,2) = 3

result = 3

Final Answer:
max(3,3) = 3

------------------------------------------------

Another Example

nums = [1,2,3,1]

Case 1 (skip last)
[1,2,3]

dp[0] = 1
dp[1] = 2
dp[2] = max(2, 3 + 1) = 4

result = 4

Case 2 (skip first)
[2,3,1]

dp[0] = 2
dp[1] = 3
dp[2] = max(3, 1 + 2) = 3

result = 3

Final Answer:
max(4,3) = 4

------------------------------------------------

TIME COMPLEXITY
O(n)

We run the house robber algorithm twice.

SPACE COMPLEXITY
O(1)

We only keep two previous values.

------------------------------------------------
*/

public class HouseRobberII {

    public static int rob(int[] nums) {

        int n = nums.length;

        if(n == 1)
            return nums[0];

        return Math.max(
                robRange(nums, 0, n-2),
                robRange(nums, 1, n-1)
        );
    }

    private static int robRange(int[] nums, int start, int end) {

        int prev2 = 0;
        int prev1 = 0;

        for(int i = start; i <= end; i++) {

            int current = Math.max(prev1, nums[i] + prev2);

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    public static void main(String[] args) {

        int[] nums = {2,3,2};

        int result = rob(nums);

        System.out.println("Maximum money robbed = " + result);
    }
}
