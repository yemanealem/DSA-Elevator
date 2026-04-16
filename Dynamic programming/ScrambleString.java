import java.util.HashMap;
import java.util.Map;

public class ScrambleString {

    // Memoization map
    private Map<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        return dfs(s1, s2);
    }

    private boolean dfs(String a, String b) {
        // If equal → valid scramble
        if (a.equals(b)) return true;

        // Memo check
        String key = a + "#" + b;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Pruning: character frequency check
        if (!isSameCharCount(a, b)) {
            memo.put(key, false);
            return false;
        }

        int n = a.length();

        // Try all split positions
        for (int k = 1; k < n; k++) {

            // Case 1: No swap
            if (dfs(a.substring(0, k), b.substring(0, k)) &&
                dfs(a.substring(k), b.substring(k))) {
                memo.put(key, true);
                return true;
            }

            // Case 2: Swap
            if (dfs(a.substring(0, k), b.substring(n - k)) &&
                dfs(a.substring(k), b.substring(0, n - k))) {
                memo.put(key, true);
                return true;
            }
        }

        memo.put(key, false);
        return false;
    }

    // Helper: check if two strings have same character frequency
    private boolean isSameCharCount(String a, String b) {
        int[] count = new int[26];

        for (int i = 0; i < a.length(); i++) {
            count[a.charAt(i) - 'a']++;
            count[b.charAt(i) - 'a']--;
        }

        for (int c : count) {
            if (c != 0) return false;
        }

        return true;
    }
}
