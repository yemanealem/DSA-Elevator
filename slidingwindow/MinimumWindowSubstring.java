import java.util.*;

public class MinimumWindowSubstring {

    /*
     * LeetCode 76: Minimum Window Substring
     * 
     * Problem:
     * Find the smallest substring in s that contains all characters of t.
     * 
     * Example:
     * s = "ADOBECODEBANC", t = "ABC"
     * Output: "BANC"
     */

    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";

        Map<Character, Integer> tCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            tCount.put(c, tCount.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> windowCount = new HashMap<>();
        int left = 0, right = 0;
        int required = tCount.size(); // number of unique chars to match
        int formed = 0;               // unique chars fully matched in window

        int minLen = Integer.MAX_VALUE;
        int start = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            windowCount.put(c, windowCount.getOrDefault(c, 0) + 1);

            if (tCount.containsKey(c) && windowCount.get(c).intValue() == tCount.get(c).intValue()) {
                formed++;
            }

            // Try to shrink the window from the left
            while (left <= right && formed == required) {
                c = s.charAt(left);

                // Update min length
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                // Remove left char from window
                windowCount.put(c, windowCount.get(c) - 1);
                if (tCount.containsKey(c) && windowCount.get(c).intValue() < tCount.get(c).intValue()) {
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
        System.out.println("Minimum window substring containing '" + t + "' is: " + result);

        // Step-by-step explanation:
        System.out.println("\nTrace (high-level window positions):");
        System.out.println("Window expands with right pointer until all characters of t are included, then left pointer shrinks to minimize length.");
        System.out.println("Example trace for this input:");
        System.out.println("Right moves: A D O B E C O D E B A N C");
        System.out.println("Left shrinks to remove unnecessary characters while window is valid");
        System.out.println("Minimum valid window found: B A N C");
    }
}
