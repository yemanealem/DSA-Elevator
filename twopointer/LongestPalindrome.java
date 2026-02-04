/**
 * Problem: Longest Palindromic Substring (LeetCode #5)
 * 
 * Given a string s, find the longest substring which is a palindrome.
 * A palindrome reads the same backward as forward.
 * 
 * Examples:
 * Input: s = "babad"
 * Output: "bab" (or "aba")
 * 
 * Input: s = "cbbd"
 * Output: "bb"
 * 
 * Approach: Two-Pointer / Expand Around Center
 * - A palindrome mirrors around its center.
 * - Each character can be the center of a palindrome:
 *     1. Odd length palindrome -> center is one character (e.g., "aba")
 *     2. Even length palindrome -> center is between two characters (e.g., "abba")
 * - For each index, expand left and right pointers around the center.
 * - Track the longest palindrome found.
 * 
 * Complexity:
 * - Time: O(n^2) in worst case
 * - Space: O(1)
 */
public class LongestPalindrome {

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s; // early return for empty or single-char strings

        int start = 0; // start index of longest palindrome
        int end = 0;   // end index of longest palindrome

        for (int i = 0; i < s.length(); i++) {
            // Odd-length palindrome centered at i
            int len1 = expandAroundCenter(s, i, i);
            // Even-length palindrome centered between i and i+1
            int len2 = expandAroundCenter(s, i, i + 1);

            int len = Math.max(len1, len2);

            // Update start and end indices if we found a longer palindrome
            if (len > end - start) {
                start = i - (len - 1) / 2; // calculate start index
                end = i + len / 2;         // calculate end index
            }
        }

        // Return the longest palindromic substring
        return s.substring(start, end + 1);
    }

    /**
     * Helper function to expand around a center
     * @param s the string
     * @param left left pointer
     * @param right right pointer
     * @return length of palindrome
     */
    private int expandAroundCenter(String s, int left, int right) {
        // Expand as long as characters match and pointers are in bounds
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Length of palindrome is right - left - 1
        return right - left - 1;
    }

    // Optional main method to test
    public static void main(String[] args) {
        LongestPalindrome lp = new LongestPalindrome();
        System.out.println(lp.longestPalindrome("babad")); // Output: "bab" or "aba"
        System.out.println(lp.longestPalindrome("cbbd"));  // Output: "bb"
        System.out.println(lp.longestPalindrome("a"));     // Output: "a"
    }
}
