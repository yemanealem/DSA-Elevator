/*
LeetCode Problem: Min Cost Climbing Stairs (Dynamic Programming)

QUESTION
You are given an integer array cost where cost[i] is the cost of stepping on the i-th stair.
After paying the cost, you can climb either 1 step or 2 steps.
You can start from step 0 or step 1.
Your goal is to reach the top of the floor (beyond the last stair) with the minimum cost.

Example:
Input: cost = [10, 15, 20]
Output: 15

HOW THE ALGORITHM WORKS
To reach any step i, there are only two possible ways:
1. From step (i-1)
2. From step (i-2)

So we use Dynamic Programming.

Formula:
dp[i] = cost[i] + min(dp[i-1], dp[i-2])

This means:
The cost to reach step i equals the cost of that step plus the minimum cost
needed to reach the previous two steps.

STEP-BY-STEP TRACE (Example: cost = [10,15,20])

Step 0:
dp[0] = 10

Step 1:
dp[1] = 15

Step 2:
dp[2] = cost[2] + min(dp[1], dp[0])
dp[2] = 20 + min(15,10)
dp[2] = 20 + 10 = 30

Now we want to reach the TOP (after the last step).
We can reach it from either step 1 or step 2.

Answer = min(dp[2], dp[1])
Answer = min(30,15)
Answer = 15

Optimal path:
Start at step 1 → pay 15 → jump 2 steps → reach top

TIME COMPLEXITY
O(n)
Because we loop through the array once.

SPACE COMPLEXITY
O(n) for the dp array.
This can be optimized to O(1) by storing only the last two values.

WHY THIS IS DYNAMIC PROGRAMMING
The problem has:
1. Optimal substructure
2. Overlapping subproblems

So we store previous results to avoid recomputation.
*/

public class MinCostClimbingStairs {

    public static int minCostClimbingStairs(int[] cost) {

        int n = cost.length;

        int[] dp = new int[n];

        dp[0] = cost[0];
        dp[1] = cost[1];

        for(int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i-1], dp[i-2]);
        }

        return Math.min(dp[n-1], dp[n-2]);
    }

    public static void main(String[] args) {

        int[] cost = {10, 15, 20};

        int result = minCostClimbingStairs(cost);

        System.out.println("Minimum Cost = " + result);
    }
}
