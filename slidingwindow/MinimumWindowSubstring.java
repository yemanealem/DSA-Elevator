public class MinimumWindowSubstring {

    /*
     * LeetCode 76: Minimum Window Substring
     * 
     * Problem:
     * Find the smallest substring in s that contains all characters of t.
     * 
     * Optimized using sliding window with fixed-size arrays (ASCII 128 chars)
     */

    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        int[] tCount = new int[128];      // counts for characters in t
        int[] windowCount = new int[128]; // counts for current window

        // Fill t counts
        for (char c : t.toCharArray()) {
            tCount[c]++;
        }

        // Number of unique characters to match
        int required = 0;
        for (int count : tCount) {
            if (count > 0) required++;
        }

        int left = 0, right = 0;
        int formed = 0; // unique chars in window that meet required count
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        System.out.println("Step-by-step sliding window trace:\n");

        while (right < s.length()) {
            char c = s.charAt(right);
            windowCount[c]++;

            // Check if current char satisfies t requirement
            if (tCount[c] > 0 && windowCount[c] == tCount[c]) {
                formed++;
            }

            // Shrink window from left if valid
            while (left <= right && formed == required) {
                char ch = s.charAt(left);

                // Update minimum length
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                // Remove left char from window
                windowCount[ch]--;
                if (tCount[ch] > 0 && windowCount[ch] < tCount[ch]) {
                    formed--;
                }

                left++;
            }

            // Trace info for current step
            System.out.println("Right: " + right + " (char '" + c + "'), Left: " + left
                    + ", Formed: " + formed + ", Current window: '" + s.substring(left-1, right+1) + "'");

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring solution = new MinimumWindowSubstring();

        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.println("Input string: " + s);
        System.out.println("Characters to include: " + t + "\n");

        String result = solution.minWindow(s, t);

        System.out.println("\nMinimum window substring containing all characters: " + result);

        /*
         * How it works (high-level trace):
         * 1. Expand right pointer until window contains all chars of t.
         * 2. Shrink left pointer to remove unnecessary chars while keeping window valid.
         * 3. Keep updating minimum window length whenever a valid window is found.
         * 
         * Example trace for s = "ADOBECODEBANC", t = "ABC":
         * Step 1: window "ADOBEC" → valid, shrink left → "DOBEC"
         * Step 2: window "BECODEBA" → valid, shrink left → "BANC"
         * Final minimum window → "BANC"
         */
    }
}
