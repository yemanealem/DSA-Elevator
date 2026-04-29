import java.util.*;

/*
 * LeetCode 514 - Freedom Trail
 *
 * Problem:
 * You are given a circular ring with characters (string `ring`) and a target keyword (string `key`).
 * You can rotate the ring clockwise or anticlockwise to align a character at index 0.
 * Each rotation step counts as 1 move, and pressing the button to select a character costs 1 move.
 *
 * Goal:
 * Return the minimum number of steps required to spell the entire `key`.
 *
 * ------------------------------------------------------------
 * Approach (Optimized Dynamic Programming):
 *
 * 1. Preprocess:
 *    - Map each character in `ring` to all its indices.
 *
 * 2. DP Definition:
 *    - dp[i][j] = minimum steps to spell key[0..j] with ring pointer at index i
 *
 * 3. Transition:
 *    - For each character key[j], we only consider its valid positions in `ring`.
 *    - For each current position `i` and previous position `k`:
 *
 *        rotation cost = min(|i - k|, n - |i - k|)
 *        dp[i][j] = min(dp[k][j-1] + rotation + 1)
 *
 *    - +1 accounts for pressing the button.
 *
 * 4. Optimization:
 *    - Only iterate over positions where characters exist (not entire ring)
 *    - Avoid expensive Math functions in inner loops
 *    - Cache map lookups
 *
 * ------------------------------------------------------------
 * Time Complexity:
 *    O(M * K^2)
 *    - M = length of key
 *    - K = average frequency of characters in ring
 *
 * Space Complexity:
 *    O(N * M)
 *    - DP table
 *
 * ------------------------------------------------------------
 */

public class FreedomTrail {

    public int findRotateSteps(String ring, String key) {
        int n = ring.length();
        int m = key.length();
        int INF = 1_000_000_000;

        // Map char -> positions
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(ring.charAt(i), k -> new ArrayList<>()).add(i);
        }

        int[][] dp = new int[n][m];

        // Initialize first character
        List<Integer> firstList = map.get(key.charAt(0));
        for (int i : firstList) {
            int diff = i > 0 ? i : -i;
            int step = diff < (n - diff) ? diff : (n - diff);
            dp[i][0] = step + 1;
        }

        for (int j = 1; j < m; j++) {
            List<Integer> currList = map.get(key.charAt(j));
            List<Integer> prevList = map.get(key.charAt(j - 1));

            for (int i : currList) {
                int best = INF;

                for (int k : prevList) {
                    int diff = i > k ? i - k : k - i;
                    int step = diff < (n - diff) ? diff : (n - diff);

                    int val = dp[k][j - 1] + step + 1;
                    if (val < best) best = val;
                }

                dp[i][j] = best;
            }
        }

        int res = INF;
        List<Integer> lastList = map.get(key.charAt(m - 1));
        for (int i : lastList) {
            if (dp[i][m - 1] < res) res = dp[i][m - 1];
        }

        return res;
    }

    public static void main(String[] args) {
        FreedomTrail sol = new FreedomTrail();
        System.out.println(sol.findRotateSteps("godding", "gd")); 
    }
}
