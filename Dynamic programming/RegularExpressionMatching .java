public class RegularExpressionMatching {

    /*
     * ============================================================
     * PROBLEM:
     * ============================================================
     * Given a string s and a pattern p, implement regular
     * expression matching with support for:
     *
     * '.' -> matches any single character
     * '*' -> matches zero or more of previous character
     *
     * The matching must cover the entire input string.
     *
     * ------------------------------------------------------------
     * EXAMPLES:
     * ------------------------------------------------------------
     * s = "aa", p = "a"   -> false
     * s = "aa", p = "a*"  -> true
     * s = "ab", p = ".*"  -> true
     *
     * ============================================================
     * HOW DYNAMIC PROGRAMMING WORKS:
     * ============================================================
     *
     * We use a DP table:
     *
     * dp[i][j]
     *
     * Meaning:
     * First i characters of string s
     * match
     * first j characters of pattern p.
     *
     * ------------------------------------------------------------
     * CASE 1: NORMAL CHARACTER MATCH OR '.'
     * ------------------------------------------------------------
     *
     * If:
     * s[i-1] == p[j-1]
     * OR
     * p[j-1] == '.'
     *
     * Then:
     *
     * dp[i][j] = dp[i-1][j-1]
     *
     * Because current characters match,
     * so we move diagonally.
     *
     * ------------------------------------------------------------
     * CASE 2: '*'
     * ------------------------------------------------------------
     *
     * '*' means:
     * zero or more of previous character.
     *
     * Example:
     * a* -> "", "a", "aa", "aaa", ...
     *
     * Two possibilities:
     *
     * 1. Ignore previous char + '*'
     *
     * dp[i][j] = dp[i][j-2]
     *
     * 2. Use '*' to match current character
     *
     * If previous pattern character matches:
     *
     * p[j-2] == s[i-1]
     * OR
     * p[j-2] == '.'
     *
     * Then:
     *
     * dp[i][j] |= dp[i-1][j]
     *
     * Meaning:
     * consume one character from string
     * while keeping same pattern.
     *
     * ============================================================
     * TIME COMPLEXITY:
     * ============================================================
     *
     * Let:
     * m = length of s
     * n = length of p
     *
     * Time Complexity:
     * O(m × n)
     *
     * Space Complexity:
     * O(m × n)
     *
     * ============================================================
     * JAVA SOLUTION:
     * ============================================================
     */

    public boolean isMatch(String s, String p) {

        int m = s.length();
        int n = p.length();

        // DP table
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Empty string matches empty pattern
        dp[0][0] = true;

        /*
         * Handle patterns like:
         * a*
         * a*b*
         * a*b*c*
         *
         * These can match empty string.
         */
        for (int j = 2; j <= n; j++) {

            if (p.charAt(j - 1) == '*') {

                dp[0][j] = dp[0][j - 2];
            }
        }

        // Fill DP table
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                /*
                 * CASE 1:
                 * Normal character match OR '.'
                 */
                if (pc == sc || pc == '.') {

                    dp[i][j] = dp[i - 1][j - 1];
                }

                /*
                 * CASE 2:
                 * '*'
                 */
                else if (pc == '*') {

                    /*
                     * OPTION 1:
                     * Ignore previous char + '*'
                     */
                    dp[i][j] = dp[i][j - 2];

                    /*
                     * OPTION 2:
                     * Use '*' to match current character
                     */
                    char prev = p.charAt(j - 2);

                    if (prev == sc || prev == '.') {

                        dp[i][j] =
                                dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        // Final answer
        return dp[m][n];
    }
}