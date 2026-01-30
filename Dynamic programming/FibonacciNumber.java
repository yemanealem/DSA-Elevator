public class FibonacciNumber {

    
    public static int fib(int n) {
        if (n <= 1) return n;

        int prev2 = 0; // F(0)
        int prev1 = 1; // F(1)

        for (int i = 2; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

    public static void main(String[] args) {
        System.out.println(fib(0)); // 0
        System.out.println(fib(1)); // 1
        System.out.println(fib(2)); // 1
        System.out.println(fib(6)); // 8
        System.out.println(fib(10)); // 55
    }
}


/*

public class FibonacciMemoization {

    
     QUESTION:
     ---------
     LeetCode: Fibonacci Number

     Fibonacci definition:
       F(0) = 0
       F(1) = 1
       F(n) = F(n-1) + F(n-2), for n >= 2

     ------------------------------------------------
     APPROACH: RECURSION + MEMOIZATION (TOP-DOWN DP)
     ------------------------------------------------
     - Plain recursion recalculates the same values
       many times → exponential time O(2^n)
     - Memoization stores results of subproblems
       so each Fibonacci number is computed once

     ------------------------------------------------
     HOW MEMOIZATION WORKS:
     ------------------------------------------------
     1. Create an array `memo` to store results
     2. If memo[n] is already computed → return it
     3. Otherwise:
          memo[n] = fib(n-1) + fib(n-2)

     ------------------------------------------------
     STEP-BY-STEP TRACE (n = 6):
     ------------------------------------------------
     fib(6)
       = fib(5) + fib(4)

     fib(5)
       = fib(4) + fib(3)

     fib(4)
       = fib(3) + fib(2)

     fib(3)
       = fib(2) + fib(1)

     fib(2)
       = fib(1) + fib(0)

     Stored in memo:
       memo[2] = 1
       memo[3] = 2
       memo[4] = 3
       memo[5] = 5
       memo[6] = 8

     Each value computed ONCE.

     ------------------------------------------------
     TIME & SPACE COMPLEXITY:
     ------------------------------------------------
     Time:  O(n)
     Space: O(n)
       - memo array
       - recursion stack

     ------------------------------------------------
     WHEN TO USE THIS:
     ------------------------------------------------
     - When recursion is natural / easier to explain
     - When interviewer wants top-down DP
     

    public static int fib(int n) {
        int[] memo = new int[n + 1];
        return helper(n, memo);
    }

    private static int helper(int n, int[] memo) {
        if (n <= 1) return n;

        if (memo[n] != 0) {
            return memo[n];
        }

        memo[n] = helper(n - 1, memo) + helper(n - 2, memo);
        return memo[n];
    }

    public static void main(String[] args) {
        System.out.println(fib(0)); // 0
        System.out.println(fib(1)); // 1
        System.out.println(fib(2)); // 1
        System.out.println(fib(6)); // 8
        System.out.println(fib(10)); // 55
    }
}








*/