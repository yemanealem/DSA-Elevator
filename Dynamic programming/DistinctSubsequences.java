public class DistinctSubsequences {

    /*
    ===========================================================
    📌 PROBLEM:
    Count number of distinct subsequences of s that equal t

    Example:
    s = "rabbbit", t = "rabbit" → Output = 3

    ===========================================================
    💡 APPROACH:
    Use Dynamic Programming (2D DP)

    dp[i][j] = ways to form first j chars of t
               using first i chars of s

    ===========================================================
    ⏱ TIME: O(n * m)
    ⏱ SPACE: O(n * m)
    ===========================================================
    */

    public int numDistinct(String s, String t) {

        int n = s.length();
        int m = t.length();

        // DP table
        long[][] dp = new long[n + 1][m + 1];

        // Base case:
        // Empty t can be formed from any prefix of s
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // Fill DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                // If characters match
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]   // take it
                             + dp[i - 1][j];     // skip it
                } else {
                    // If not match → skip character from s
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return (int) dp[n][m];
    }
}
