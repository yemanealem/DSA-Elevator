/*
 * LeetCode 467 - Unique Substrings in Wraparound String
 *
 * Problem:
 * Given a string p, return the number of unique non-empty substrings of p
 * that are present in the infinite wraparound string:
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz..."
 *
 * A valid substring must follow consecutive characters in wraparound order.
 *
 * ------------------------------------------------------------
 * Approach (Greedy + DP):
 *
 * 1. Use an array count[26]:
 *    - count[i] = maximum length of valid substring ending with ('a' + i)
 *
 * 2. Traverse string:
 *    - If current char follows previous in wraparound:
 *        increase current length
 *      Else:
 *        reset length = 1
 *
 * 3. Update:
 *    count[currentChar] = max(count[currentChar], currentLength)
 *
 * 4. Result:
 *    sum(count[i]) gives total unique substrings
 *
 * ------------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 * ------------------------------------------------------------
 */

public class UniqueWraparound {

    public int findSubstringInWraproundString(String p) {
        int[] count = new int[26];
        int len = 0;

        for (int i = 0; i < p.length(); i++) {
            if (i > 0 &&
               ((p.charAt(i) - p.charAt(i - 1) == 1) ||
               (p.charAt(i - 1) == 'z' && p.charAt(i) == 'a'))) {
                len++;
            } else {
                len = 1;
            }

            int index = p.charAt(i) - 'a';
            if (len > count[index]) {
                count[index] = len;
            }
        }

        int result = 0;
        for (int c : count) {
            result += c;
        }

        return result;
    }

    public static void main(String[] args) {
        UniqueWraparound sol = new UniqueWraparound();
        System.out.println(sol.findSubstringInWraproundString("zab")); // Output: 6
    }
}
