/*
===========================================================
📌 PROBLEM: Divisor Game

Alice starts with n.
Players subtract a divisor x (x < n, n % x == 0).
Player who cannot move loses.

Return true if Alice wins.

===========================================================
🧠 HOW IT WORKS

dp[i] = true if current player wins with i

For each i:
- Try all divisors x
- If any move leads to dp[i - x] == false → win

===========================================================
⏱ RUNNING TIME

Time:  O(n √n)
Space: O(n)

===========================================================
*/

class Solution {

    public boolean divisorGame(int n) {

        boolean[] dp = new boolean[n + 1];

        dp[1] = false; // base case

        for (int i = 2; i <= n; i++) {

            for (int x = 1; x < i; x++) {

                if (i % x == 0) {

                    // if opponent loses, current wins
                    if (!dp[i - x]) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }

        return dp[n];
    }
}