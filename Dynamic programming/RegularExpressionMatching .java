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
     * ------------------------------------------------------------
     * CASE 2: '*'
     * ------------------------------------------------------------
     *
     * '*' means:
     * zero or more of previous character.
     *
     * Two possibilities:
     *
     * 1. Ignore previous char + '*'
     *
     * dp[i][j] = dp[i][j-2]
     *
     * 2. Use '*' to match current character
     *
     * If:
     * p[j-2] == s[i-1]
     * OR
     * p[j-2] == '.'
     *
     * Then:
     *
     * dp[i][j] |= dp[i-1][j]
     *
     * ============================================================
     * TIME COMPLEXITY:
     * ============================================================
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
                 * Normal character or '.'
                 */
                if (pc == sc || pc == '.') {

                    dp[i][j] = dp[i - 1][j - 1];
                }

                /*
                 * CASE 2:
                 * '*'
                 */
                else if (pc == '*') {

                    // Ignore previous character + '*'
                    dp[i][j] = dp[i][j - 2];

                    // Match one or more characters
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

    /*
     * ============================================================
     * MAIN METHOD:
     * ============================================================
     */
    public static void main(String[] args) {

        RegularExpressionMatching solution =
                new RegularExpressionMatching();

        String s1 = "aa";
        String p1 = "a";

        String s2 = "aa";
        String p2 = "a*";

        String s3 = "ab";
        String p3 = ".*";

        System.out.println(
                "Input: s = " + s1 + ", p = " + p1);

        System.out.println(
                "Output: " + solution.isMatch(s1, p1));

        System.out.println();

        System.out.println(
                "Input: s = " + s2 + ", p = " + p2);

        System.out.println(
                "Output: " + solution.isMatch(s2, p2));

        System.out.println();

        System.out.println(
                "Input: s = " + s3 + ", p = " + p3);

        System.out.println(
                "Output: " + solution.isMatch(s3, p3));
    }
}