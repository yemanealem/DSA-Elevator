/**
 * Problem: Replace the Substring for Balanced String
 *
 * You are given a string s containing only the characters 'Q', 'W', 'E', and 'R'.
 * A string is considered balanced if each character appears exactly n / 4 times,
 * where n is the length of the string.
 *
 * Your task is to find the minimum length of a substring that can be replaced
 * to make the entire string balanced.
 *
 * Example:
 * Input: s = "QQWE"
 * Output: 1
 * Explanation: Replacing "Q" with "R" makes the string balanced: "RQWE"
 *
 * ------------------------------------------------------------
 * How it works (Sliding Window Approach):
 *
 * 1. Count the frequency of each character in the string.
 * 2. Compute the target: each character should appear n / 4 times.
 * 3. Identify "excess" characters (those exceeding the target).
 * 4. Use a sliding window:
 *    - Expand the right pointer and decrease counts.
 *    - While the outside of the window is balanced (all counts <= target),
 *      try shrinking the window from the left.
 * 5. Track the minimum window size that satisfies the condition.
 *
 * The window represents the substring we will replace.
 * We want the smallest such window.
 *
 * ------------------------------------------------------------
 * Running Time:
 *
 * Time Complexity: O(n)
 * - Each character is visited at most twice (once by right pointer,
 *   once by left pointer).
 *
 * Space Complexity: O(1)
 * - We only use a fixed array of size 4 (for Q, W, E, R).
 */

public class BalancedString {

    public int balancedString(String s) {
        int n = s.length();
        int target = n / 4;

        int[] count = new int[4];

        // Count frequency of each character
        for (char c : s.toCharArray()) {
            count[index(c)]++;
        }

        // If already balanced
        if (isBalanced(count, target)) return 0;

        int left = 0;
        int minLen = n;

        // Sliding window
        for (int right = 0; right < n; right++) {
            count[index(s.charAt(right))]--;

            // Shrink window while valid
            while (left <= right && isBalanced(count, target)) {
                minLen = Math.min(minLen, right - left + 1);
                count[index(s.charAt(left))]++;
                left++;
            }
        }

        return minLen;
    }

    // Check if current counts are balanced
    private boolean isBalanced(int[] count, int target) {
        return count[0] <= target &&
               count[1] <= target &&
               count[2] <= target &&
               count[3] <= target;
    }

    // Map characters to array indices
    private int index(char c) {
        switch (c) {
            case 'Q': return 0;
            case 'W': return 1;
            case 'E': return 2;
            case 'R': return 3;
            default: return -1;
        }
    }

    public static void main(String[] args) {
        BalancedString sol = new BalancedString();

        System.out.println(sol.balancedString("QWER")); // 0
        System.out.println(sol.balancedString("QQWE")); // 1
        System.out.println(sol.balancedString("QQQW")); // 2
    }
}
