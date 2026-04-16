public class DistinctSubsequences {

    /*
    ===========================================================
    🚀 OPTIMIZED VERSION (FAST)
    ===========================================================
    Improvements:
    - 1D DP → less memory
    - char[] → faster access
    - j <= i → avoid useless computation
    ===========================================================
    */

    public int numDistinct(String s, String t) {

        int n = s.length();
        int m = t.length();

        // If target is longer → impossible
        if (m > n) return 0;

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        long[] dp = new long[m + 1];
        dp[0] = 1; // empty target

        for (int i = 1; i <= n; i++) {

            // Important: go backwards
            for (int j = Math.min(i, m); j >= 1; j--) {

                if (sArr[i - 1] == tArr[j - 1]) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return (int) dp[m];
    }
}
