/*
 * LeetCode 467 - Unique Substrings in Wraparound String
 *
 * Problem:
 * Count unique non-empty substrings of p that exist in the infinite
 * wraparound string "...abcdefghijklmnopqrstuvwxyz..."
 *
 * Approach:
 * - count[i] = max length of valid substring ending with ('a' + i)
 * - If current char follows previous in wraparound:
 *      len++
 *   else:
 *      len = 1
 * - Update count for each character
 *
 * Time: O(n)
 * Space: O(1)
 */

public class UniqueWraparound {

    public int findSubstringInWraproundString(String p) {
        int[] count = new int[26];
        int len = 0;

        char[] arr = p.toCharArray();

        for (int i = 0; i < arr.length; i++) {

            if (i > 0 && (arr[i] - arr[i - 1] + 26) % 26 == 1) {
                len++;
            } else {
                len = 1;
            }

            int idx = arr[i] - 'a';
            if (len > count[idx]) {
                count[idx] = len;
            }
        }

        int res = 0;
        for (int v : count) res += v;

        return res;
    }

    public static void main(String[] args) {
        UniqueWraparound sol = new UniqueWraparound();
        System.out.println(sol.findSubstringInWraproundString("zab")); 
    }
}
