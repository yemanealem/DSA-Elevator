import java.util.*;

/**
 * LeetCode 464: Can I Win
 *
 * Problem:
 * Two players take turns picking numbers from 1..maxChoosableInteger.
 * The first player to reach or exceed desiredTotal wins.
 *
 * How it works:
 * - Use DFS with memoization.
 * - Represent picked numbers using a bitmask (int).
 * - For each unpicked number, pick it and check recursively if the opponent loses.
 * - Memoize results for each state to avoid recomputation.
 *
 * Running time:
 * - Worst-case O(2^n * n), since there are 2^n states and each has up to n choices.
 * - n ≤ 20, so feasible (~1 million states max).
 */

public class CanIWin {

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) return true;

        int maxSum = maxChoosableInteger * (maxChoosableInteger + 1) / 2;
        if (maxSum < desiredTotal) return false;

        Map<Integer, Boolean> memo = new HashMap<>();
        return dfs(0, maxChoosableInteger, desiredTotal, memo);
    }

    private boolean dfs(int state, int n, int remaining, Map<Integer, Boolean> memo) {
        if (memo.containsKey(state)) return memo.get(state);

        for (int i = 1; i <= n; i++) {
            int mask = 1 << (i - 1);
            if ((state & mask) == 0) { // number i not used
                if (i >= remaining || !dfs(state | mask, n, remaining - i, memo)) {
                    memo.put(state, true);
                    return true; // current player can win
                }
            }
        }

        memo.put(state, false);
        return false; // current player loses
    }

    public static void main(String[] args) {
        CanIWin solver = new CanIWin();
        int maxChoosableInteger = 10;
        int desiredTotal = 11;
        System.out.println("Can first player win? " +
                solver.canIWin(maxChoosableInteger, desiredTotal)); // true
    }
}
