/*
===========================================================
📌 PROBLEM: Divisor Game

Alice starts with n.
Players subtract a divisor x (0 < x < n, n % x == 0).
Player who cannot move loses.

Return true if Alice wins.

===========================================================
🧠 IDEA (Dynamic Programming)

dp[i] = true if current player wins with i

Try all valid divisors x:
If any move leads to dp[i - x] == false → win

===========================================================
⏱ TIME COMPLEXITY:
O(n √n)

📦 SPACE COMPLEXITY:
O(n)

===========================================================
*/

public class DivisorGameDP {

    public static boolean divisorGame(int n) {

        boolean[] dp = new boolean[n + 1];

        dp[1] = false; // no move possible

        for (int i = 2; i <= n; i++) {

            for (int x = 1; x < i; x++) {

                if (i % x == 0) {

                    if (!dp[i - x]) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {

        System.out.println(divisorGame(10)); // true
    }
}