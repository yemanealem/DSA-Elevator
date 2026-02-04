class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0; // start index of longest palindrome
        int end = 0;   // end index of longest palindrome

        for (int i = 0; i < s.length(); i++) {
            // Odd-length palindrome (centered at i)
            int len1 = expandAroundCenter(s, i, i);
            // Even-length palindrome (centered between i and i+1)
            int len2 = expandAroundCenter(s, i, i + 1);

            // Maximum length of palindrome found at this center
            int len = Math.max(len1, len2);

            // Update start and end indices if a longer palindrome is found
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        // Return substring from start to end inclusive
        return s.substring(start, end + 1);
    }

    // Helper function: expand around center and return palindrome length
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // length = right - left - 1
        return right - left - 1;
    }
}