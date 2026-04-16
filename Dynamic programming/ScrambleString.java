import java.util.HashMap;
import java.util.Map;

/*
===========================================================
📌 PROBLEM: Scramble String (LeetCode)

Given two strings s1 and s2 of equal length,
determine if s2 is a scrambled string of s1.

A scramble string is formed by:
1. Splitting the string into two non-empty parts
2. Recursively swapping or keeping parts

Example:
s1 = "great"
s2 = "rgeat"
Output: true

===========================================================
💡 APPROACH:

- Use recursion to try all possible splits
- At each split, check:
    1. No swap case
    2. Swap case

- Use memoization (HashMap) to avoid recomputation
- Use character frequency check to prune invalid cases early

===========================================================
⏱ TIME COMPLEXITY: O(n^4)
⏱ SPACE COMPLEXITY: O(n^3)
===========================================================
*/

public class ScrambleString {

    // Memoization map to store computed results
    private Map<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {

        // If lengths are different → cannot be scramble
        if (s1.length() != s2.length()) return false;

        return dfs(s1, s2);
    }

    private boolean dfs(String a, String b) {

        // ✅ Base Case: if both strings are equal
        if (a.equals(b)) return true;

        // Create unique key for memoization
        String key = a + "#" + b;

        // ✅ Return cached result if already computed
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // ✅ Pruning: if character counts differ → impossible
        if (!isSameCharCount(a, b)) {
            memo.put(key, false);
            return false;
        }

        int n = a.length();

        // 🔁 Try all possible split positions
        for (int k = 1; k < n; k++) {

            /*
            -----------------------------------------
            Case 1: NO SWAP
            a[0:k] ↔ b[0:k]
            a[k:n] ↔ b[k:n]
            -----------------------------------------
            */
            if (dfs(a.substring(0, k), b.substring(0, k)) &&
                dfs(a.substring(k), b.substring(k))) {

                memo.put(key, true);
                return true;
            }

            /*
            -----------------------------------------
            Case 2: SWAP
            a[0:k] ↔ b[n-k:n]
            a[k:n] ↔ b[0:n-k]
            -----------------------------------------
            */
            if (dfs(a.substring(0, k), b.substring(n - k)) &&
                dfs(a.substring(k), b.substring(0, n - k))) {

                memo.put(key, true);
                return true;
            }
        }

        // ❌ If no valid split found
        memo.put(key, false);
        return false;
    }

    /*
    ---------------------------------------------------
    🔧 Helper Method:
    Check if two strings have same character frequency
    (Used for pruning to improve performance)
    ---------------------------------------------------
    */
    private boolean isSameCharCount(String a, String b) {

        int[] count = new int[26]; // assuming lowercase letters

        for (int i = 0; i < a.length(); i++) {
            count[a.charAt(i) - 'a']++;
            count[b.charAt(i) - 'a']--;
        }

        // If any count is not zero → mismatch
        for (int c : count) {
            if (c != 0) return false;
        }

        return true;
    }
}
