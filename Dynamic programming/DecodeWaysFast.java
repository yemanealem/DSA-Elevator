/*
==========================================
📌 PROBLEM: Decode Ways

Mapping:
1 -> A, 2 -> B, ..., 26 -> Z

We must count number of ways to decode a digit string.

==========================================
🧠 OPTIMIZED IDEA

Avoid substring + parsing.
Use direct char math:

- single digit: s[i] != '0'
- two digits: (s[i-1] == '1') OR
              (s[i-1] == '2' AND s[i] <= '6')

==========================================
⏱ COMPLEXITY

Time:  O(n)
Space: O(1)

==========================================
*/

public class DecodeWaysFast {

    public static int numDecodings(String s) {

        if (s == null || s.length() == 0 || s.charAt(0) == '0')
            return 0;

        int n = s.length();

        int prev2 = 1; // dp[i-2]
        int prev1 = 1; // dp[i-1]

        for (int i = 1; i < n; i++) {

            int curr = 0;

            char currChar = s.charAt(i);
            char prevChar = s.charAt(i - 1);

            // Case 1: single digit decode
            if (currChar != '0') {
                curr += prev1;
            }

            // Case 2: two digit decode (FAST CHECK)
            if (prevChar == '1' ||
               (prevChar == '2' && currChar <= '6')) {
                curr += prev2;
            }

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("226")); // 3
    }
}