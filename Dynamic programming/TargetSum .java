/*
LeetCode Problem: Target Sum (Dynamic Programming)

QUESTION
You are given an integer array nums and an integer target.

You must assign either a '+' or '-' sign to every number in nums,
then compute the final expression.

Return the number of different ways to assign the signs so that
the total expression equals target.

Example 1
Input: nums = [1,1,1,1,1], target = 3
Output: 5

Explanation
There are 5 ways:

+1 +1 +1 +1 -1 = 3
+1 +1 +1 -1 +1 = 3
+1 +1 -1 +1 +1 = 3
+1 -1 +1 +1 +1 = 3
-1 +1 +1 +1 +1 = 3

-----------------------------------------------------

KEY IDEA

Each number can either be:

+nums[i]
or
-nums[i]

Instead of checking all combinations (which is exponential),
we convert the problem to a SUBSET SUM problem.

Let:

P = sum of numbers assigned '+'
N = sum of numbers assigned '-'

We know:

P - N = target
P + N = totalSum

Add both equations:

2P = target + totalSum

So:

P = (target + totalSum) / 2

Now the problem becomes:

"Find the number of subsets whose sum = P"

-----------------------------------------------------

STEP-BY-STEP TRACE

Example:
nums = [1,1,1,1,1]
target = 3

Step 1
Total sum = 5

Step 2
P = (target + totalSum) / 2
P = (3 + 5) / 2
P = 4

Now we must count subsets whose sum = 4

-----------------------------------------------------

SUBSETS THAT SUM TO 4

1+1+1+1 = 4

Choose any 4 of the 5 ones

Total ways = 5

-----------------------------------------------------

DYNAMIC PROGRAMMING

dp[i] = number of ways to create sum i

Initialize:

dp[0] = 1
(because empty subset forms sum 0)

For each number:

update dp from right → left

dp[i] += dp[i - num]

-----------------------------------------------------

DP TRACE

nums = [1,1,1,1,1]
target sum = 4

Initial
dp = [1,0,0,0,0]

After first 1
dp = [1,1,0,0,0]

After second 1
dp = [1,2,1,0,0]

After third 1
dp = [1,3,3,1,0]

After fourth 1
dp = [1,4,6,4,1]

After fifth 1
dp = [1,5,10,10,5]

Answer = dp[4] = 5

-----------------------------------------------------

TIME COMPLEXITY
O(n * P)

n = number of elements
P = transformed subset target

-----------------------------------------------------

SPACE COMPLEXITY
O(P)

-----------------------------------------------------

WHY THIS IS DYNAMIC PROGRAMMING

The problem has:

1. Overlapping subproblems
2. Optimal substructure

So we reuse previously computed subset counts.
-----------------------------------------------------
*/

public class TargetSum {

    public static int findTargetSumWays(int[] nums, int target) {

        int totalSum = 0;

        for(int num : nums)
            totalSum += num;

        // impossible case
        if((target + totalSum) % 2 != 0 || Math.abs(target) > totalSum)
            return 0;

        int subsetSum = (target + totalSum) / 2;

        int[] dp = new int[subsetSum + 1];

        dp[0] = 1;

        for(int num : nums) {

            for(int i = subsetSum; i >= num; i--) {

                dp[i] += dp[i - num];
            }
        }

        return dp[subsetSum];
    }

    public static void main(String[] args) {

        int[] nums = {1,1,1,1,1};
        int target = 3;

        int result = findTargetSumWays(nums, target);

        System.out.println("Number of ways = " + result);
    }
}
