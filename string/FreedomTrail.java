import java.util.*;

public class FreedomTrail {

    public int findRotateSteps(String ring, String key) {
        int n = ring.length();
        int m = key.length();

        // Map char -> positions
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(ring.charAt(i), k -> new ArrayList<>()).add(i);
        }

        // dp[i][j] = min steps to spell key[0..j] with ring at position i
        int[][] dp = new int[n][m];

        // Initialize for first character
        for (int i = 0; i < n; i++) {
            if (ring.charAt(i) == key.charAt(0)) {
                int diff = Math.abs(i - 0);
                dp[i][0] = Math.min(diff, n - diff) + 1;
            } else {
                dp[i][0] = Integer.MAX_VALUE;
            }
        }

        // Fill DP
        for (int j = 1; j < m; j++) {
            for (int i : map.get(key.charAt(j))) {
                dp[i][j] = Integer.MAX_VALUE;

                for (int k : map.get(key.charAt(j - 1))) {
                    int diff = Math.abs(i - k);
                    int step = Math.min(diff, n - diff);

                    dp[i][j] = Math.min(dp[i][j], dp[k][j - 1] + step + 1);
                }
            }
        }

        // Result = min in last column
        int res = Integer.MAX_VALUE;
        for (int i : map.get(key.charAt(m - 1))) {
            res = Math.min(res, dp[i][m - 1]);
        }

        return res;
    }

    public static void main(String[] args) {
        FreedomTrail sol = new FreedomTrail();
        System.out.println(sol.findRotateSteps("godding", "gd")); // 4
    }
}
