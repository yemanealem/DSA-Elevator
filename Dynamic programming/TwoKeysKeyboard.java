/*
Question:
---------
LeetCode 650 - 2 Keys Keyboard

Initially there is only one character 'A' on the screen.

You can perform only two operations:

1. Copy All
2. Paste

Return the minimum number of operations needed
to get exactly n 'A's.


Dynamic Programming Idea:
-------------------------
dp[i] = minimum operations needed to get i 'A's.

For every number i:
- Find a divisor j of i.
- If j divides i:
    We can:
    1. Build j first
    2. Copy All once
    3. Paste (i/j - 1) times

So:

dp[i] = dp[j] + (i/j)

We try all divisors and take the minimum.


Optimization:
-------------
Instead of checking all numbers from 1 to i,
we only check divisors up to sqrt(i).

Also, once we find the largest divisor,
we can break early because it gives
the minimum operations faster.


Example:
--------
n = 9

Build 3 A's first:
AAA

Copy All
Paste twice

AAA -> AAAAAA -> AAAAAAAAA

Operations:
dp[3] + 3 = 6


Time Complexity:
----------------
O(n * sqrt(n))

Space Complexity:
-----------------
O(n)
*/

public class TwoKeysKeyboard {

    public static int minSteps(int n) {

        // dp[i] = minimum operations to get i A's
        int[] dp = new int[n + 1];

        for (int i = 2; i <= n; i++) {

            // Worst case:
            // Copy once + paste (i - 1) times
            dp[i] = i;

            // Find largest divisor quickly
            for (int j = i / 2; j >= 2; j--) {

                // If j divides i
                if (i % j == 0) {

                    /*
                     Build j first
                     Copy All once
                     Paste (i/j - 1) times

                     Total:
                     dp[j] + i/j
                    */
                    dp[i] = dp[j] + (i / j);

                    // First largest divisor gives optimal answer
                    break;
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {

        int n = 12;

        System.out.println(minSteps(n));
    }
}