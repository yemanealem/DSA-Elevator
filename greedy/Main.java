/**
 * LeetCode #44 - Wildcard Matching
 * 
 * Problem:
 * Given a string s and a pattern p containing:
 *  - '?' : matches any single character
 *  - '*' : matches any sequence of characters (including empty)
 * Determine if s matches p.
 * 
 * Approach (Greedy):
 * - Use two pointers i (for s) and j (for p)
 * - Track last '*' position in pattern (starIdx) and corresponding s position (sTmpIdx)
 * - Iterate through s:
 *     1. If s[i] matches p[j] or p[j] == '?', move both pointers
 *     2. If p[j] == '*', record starIdx = j, sTmpIdx = i, move j
 *     3. If mismatch but previous '*', backtrack: i = sTmpIdx + 1, j = starIdx + 1, sTmpIdx++
 *     4. Else, return false
 * - After loop, skip remaining '*' in pattern
 *
 * Trace Example:
 * s = "adceb", p = "*a*b"
 * i=0,j=0 -> '*' found, starIdx=0, sTmpIdx=0, move j=1
 * i=0,j=1 -> s[0]='a' matches p[1]='a', i=1,j=2
 * i=1,j=2 -> '*' found, starIdx=2, sTmpIdx=1, j=3
 * i=1,j=3 -> mismatch, backtrack to i=sTmpIdx+1=2, j=starIdx+1=3
 * Continue until match → return true
 */

// LeetCode-style Solution class
class Solution {
    public boolean isMatch(String s, String p) {
        int i = 0, j = 0;
        int starIdx = -1, sTmpIdx = -1;
        int sLen = s.length(), pLen = p.length();

        while (i < sLen) {
            // Case 1: characters match or '?'
            if (j < pLen && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                i++;
                j++;
            }
            // Case 2: '*' found in pattern
            else if (j < pLen && p.charAt(j) == '*') {
                starIdx = j;
                sTmpIdx = i;
                j++;
            }
            // Case 3: mismatch but previous '*' exists → backtrack
            else if (starIdx != -1) {
                j = starIdx + 1;
                sTmpIdx++;
                i = sTmpIdx;
            }
            // Case 4: mismatch and no '*', cannot match
            else {
                return false;
            }
        }

        // Skip remaining '*' in pattern
        while (j < pLen && p.charAt(j) == '*') {
            j++;
        }

        return j == pLen;
    }
}

// Main class to test the solution locally
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String s1 = "adceb", p1 = "*a*b";
        System.out.println("Match 1? " + solution.isMatch(s1, p1)); // true

        String s2 = "acdcb", p2 = "a*c?b";
        System.out.println("Match 2? " + solution.isMatch(s2, p2)); // false

        String s3 = "aa", p3 = "a*";
        System.out.println("Match 3? " + solution.isMatch(s3, p3)); // true

        String s4 = "mississippi", p4 = "m??*ss*?i*pi";
        System.out.println("Match 4? " + solution.isMatch(s4, p4)); // true
    }
}
