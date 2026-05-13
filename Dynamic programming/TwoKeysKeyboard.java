/*
Question:
---------
LeetCode 650 - 2 Keys Keyboard

Initially, there is only one character 'A' on the screen.

You can perform only 2 operations:

1. Copy All
   - Copies all characters currently on the screen.

2. Paste
   - Pastes the copied characters onto the screen.

Given an integer n,
return the minimum number of operations needed
to get exactly n 'A's on the screen.


Example:
--------
Input:
n = 3

Output:
3

Explanation:
1. Copy All
2. Paste
3. Paste

Result = "AAA"


How Dynamic Programming Works:
------------------------------
dp[i] = minimum operations needed to get i characters.

For every number i:
- Find a divisor j of i.
- If j divides i, we can:
    1. Build j characters first.
    2. Copy All once.
    3. Paste (i/j - 1) times.

Operations:
dp[j] + 1 + (i/j - 1)

Simplified:
dp[j] + i/j

We try all possible divisors and take the minimum.


Example:
--------
i = 9

Divisors:
1, 3

Using 3:
- Build 3 A's
- Copy All
- Paste 2 times

Total:
dp[3] + 3


Time Complexity:
----------------
O(n * sqrt(n))

Because for every number,
we check divisors up to sqrt(i).


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
            // Copy once + paste (i-1) times
            dp[i] = i;

            // Find divisors
            for (int j = 2; j * j <= i; j++) {

                // j is divisor of i
                if (i % j == 0) {

                    // Option 1:
                    // Build j first
                    dp[i] = Math.min(dp[i],
                            dp[j] + (i / j));

                    // Option 2:
                    // Build i/j first
                    dp[i] = Math.min(dp[i],
                            dp[i / j] + j);
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {

        int n = 9;

        System.out.println(minSteps(n));
    }
}