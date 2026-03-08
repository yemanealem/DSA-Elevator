/**
 * Permutation in String
 *
 * Problem:
 * Check if s2 contains a permutation of s1 using sliding window.
 *
 * How it works:
 * 1. Create frequency array of s1 (count1).
 * 2. Slide a window of size s1.length() over s2 and maintain its frequency (count2).
 * 3. If at any point count1 equals count2, return true.
 *
 * Example:
 * s1 = "ab", s2 = "eidbaooo"
 * Window "ba" matches "ab" -> return true
 *
 * Running Time: O(n), Space: O(26)
 */
class PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) return false;

        int[] count1 = new int[26];
        int[] count2 = new int[26];

        // Count chars in s1
        for (char c : s1.toCharArray()) count1[c - 'a']++;

        // Initialize first window in s2
        for (int i = 0; i < len1; i++) count2[s2.charAt(i) - 'a']++;

        if (matches(count1, count2)) return true;

        // Slide window
        for (int i = len1; i < len2; i++) {
            count2[s2.charAt(i) - 'a']++;               // add right char
            count2[s2.charAt(i - len1) - 'a']--;       // remove left char
            if (matches(count1, count2)) return true;
        }

        return false;
    }

    // Compare two frequency arrays
    private boolean matches(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) if (a[i] != b[i]) return false;
        return true;
    }

    public static void main(String[] args) {
        PermutationInString sol = new PermutationInString();
        String s1 = "ab", s2 = "eidbaooo";
        System.out.println(sol.checkInclusion(s1, s2)); // true
    }
}
