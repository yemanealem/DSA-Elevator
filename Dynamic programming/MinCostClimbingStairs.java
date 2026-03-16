/*
LeetCode: Min Cost Climbing Stairs

You can climb either 1 or 2 steps.
Each step has a cost.
Find the minimum cost to reach the top.

Example:
cost = [10,15,20]

Path:
Start at step 1 -> pay 15 -> jump 2 steps -> reach top
Answer = 15
*/

public class MinCostClimbingStairs {

    public static int minCostClimbingStairs(int[] cost) {

        int prev2 = cost[0]; // cost to reach step 0
        int prev1 = cost[1]; // cost to reach step 1

        for(int i = 2; i < cost.length; i++) {

            int current = cost[i] + Math.min(prev1, prev2);

            prev2 = prev1;
            prev1 = current;
        }

        return Math.min(prev1, prev2);
    }

    public static void main(String[] args) {

        int[] cost = {10,15,20};

        int result = minCostClimbingStairs(cost);

        System.out.println("Minimum Cost = " + result);
    }
}
