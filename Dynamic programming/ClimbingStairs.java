public class ClimbingStairs {

    /*
     QUESTION:
     ---------
     LeetCode: Climbing Stairs

     You are climbing a staircase with n steps.
     Each time you can climb either:
       - 1 step
       - 2 steps

     Return the number of distinct ways to reach the top.

     ------------------------------------------------
     KEY IDEA (DYNAMIC PROGRAMMING):
     ------------------------------------------------
     To reach step n:
       - You can come from step (n-1) by taking 1 step
       - You can come from step (n-2) by taking 2 steps

     So the total number of ways:
         dp[n] = dp[n-1] + dp[n-2]

     This is the same recurrence as Fibonacci.

     ------------------------------------------------
     BASE CASES:
     ------------------------------------------------
     dp[0] = 1  → one way (stay where you are)
     dp[1] = 1  → only one way: 1 step

     ------------------------------------------------
     STEP-BY-STEP EXAMPLE (n = 5):
     ------------------------------------------------
     dp[0] = 1
     dp[1] = 1
     dp[2] = 2  → (1+1, 2)
     dp[3] = 3  → (1+1+1, 1+2, 2+1)
     dp[4] = 5
     dp[5] = 8

     Answer = 8 ways

     ------------------------------------------------
     TIME & SPACE COMPLEXITY:
     ------------------------------------------------
     Time:  O(n)
     Space: O(n)  (can be optimized to O(1))

     ------------------------------------------------
     WHY DYNAMIC PROGRAMMING?
     ------------------------------------------------
     - Recursive solution repeats the same work
     - DP stores results of subproblems
     - Builds solution from bottom to top
    */

    public static int climbStairs(int n) {
        if (n <= 1) return 1;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(4)); // 4
        System.out.println(climbStairs(3)); // 3
        System.out.println(climbStairs(5)); // 8
    }
}
