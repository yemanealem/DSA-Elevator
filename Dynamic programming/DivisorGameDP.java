class Solution {

    Boolean[] dp;

    public boolean divisorGame(int n) {
        dp = new Boolean[n + 1];
        return dfs(n);
    }

    private boolean dfs(int n) {

        if (n == 1) return false;

        if (dp[n] != null) return dp[n];

        // try all valid moves
        for (int x = 1; x < n; x++) {

            if (n % x == 0) {

                // if opponent loses, current wins
                if (!dfs(n - x)) {
                    return dp[n] = true;
                }
            }
        }

        return dp[n] = false;
    }
}