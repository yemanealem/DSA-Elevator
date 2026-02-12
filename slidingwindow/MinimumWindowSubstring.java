public class MinimumWindowSubstring {

    /*
     * LeetCode 76: Minimum Window Substring
     * 
     * Problem:
     * Find the smallest substring in s that contains all characters of t.
     * 
     * Optimized using sliding window with fixed-size arrays (ASCII 128 chars)
     * 
     * Example:
     * s = "ADOBECODEBANC", t = "ABC"
     * Output: "BANC"
     */

    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        int[] tCount = new int[128];      // counts for t
        int[] windowCount = new int[128]; // counts for current window

        // Fill t counts
        for (char c : t.toCharArray()) {
            tCount[c]++;
        }

        int required = 0;
        for (int count : tCount) {
            if (count > 0) required++;
        }

        int left = 0, right = 0;
        int formed = 0; // number of chars in window that meet tCount requirement
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        System.out.println("Sliding Window Trace:");
        while (right < s.length()) {
            char c = s.charAt(right);
            windowCount[c]++;

            // Check if current char satisfies t requirement
            if (tCount[c] > 0 && windowCount[c] == tCount[c]) {
                formed++;
            }

            // Try to shrink the window from the left
            while (left <= right && formed == required) {
                char ch = s.charAt(left);

                // Update minimum length
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                windowCount[ch]--;
                if (tCount[ch] > 0 && windowCount[ch] < tCount[ch]) {
                    formed--;
                }

                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring solution = new MinimumWindowSubstring();

        String s = "ADOBECODEBANC";
        String t = "ABC";

        String result = solution.minWindow(s, t);

        System.out.println("\nInput string: " + s);
        System.out.println("Characters to include: " + t);
        System.out.println("Minimum window substring containing all characters: " + result);

        /*
         * Step-by-step (high-level) sliding window trace:
         * - Expand right pointer until all characters in t are included in the window.
         * - Shrink left pointer as much as possible while window remains valid.
         * - Update minimum length whenever window is valid.
         * Example for this input:
         * Right moves: A D O B E C O D E B A N C
         * Left shrinks to remove unnecessary chars
         * Minimum valid window found: B A N C
         */
    }
}
