public class CanIWinOptimized {

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int n = maxChoosableInteger;
        int maxSum = n * (n + 1) / 2;
        if (desiredTotal <= 0) return true;
        if (maxSum < desiredTotal) return false;

        Boolean[] memo = new Boolean[1 << n]; // 2^n states
        return dfs(0, n, desiredTotal, memo);
    }

    private boolean dfs(int state, int n, int remaining, Boolean[] memo) {
        if (memo[state] != null) return memo[state];

        // Try picking numbers from largest to smallest (pruning)
        for (int i = n; i >= 1; i--) {
            int mask = 1 << (i - 1);
            if ((state & mask) == 0) { // number i not used
                // If picking i reaches target OR opponent loses
                if (i >= remaining || !dfs(state | mask, n, remaining - i, memo)) {
                    memo[state] = true;
                    return true;
                }
            }
        }

        memo[state] = false;
        return false;
    }

    public static void main(String[] args) {
        CanIWinOptimized solver = new CanIWinOptimized();
        int maxChoosableInteger = 10;
        int desiredTotal = 11;
        System.out.println("Can first player win? " +
                solver.canIWin(maxChoosableInteger, desiredTotal)); // true
    }
}
