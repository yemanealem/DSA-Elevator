public class Solution {
    public int balancedString(String s) {
        int n = s.length();
        int target = n / 4;

        int[] count = new int[128]; // ASCII

        // Count all characters
        for (char c : s.toCharArray()) {
            count[c]++;
        }

        // If already balanced
        if (isBalanced(count, target)) return 0;

        int left = 0;
        int minLen = n;

        for (int right = 0; right < n; right++) {
            count[s.charAt(right)]--;

            // Shrink window while valid
            while (left <= right && isBalanced(count, target)) {
                minLen = Math.min(minLen, right - left + 1);
                count[s.charAt(left)]++;
                left++;
            }
        }

        return minLen;
    }

    private boolean isBalanced(int[] count, int target) {
        return count['Q'] <= target &&
               count['W'] <= target &&
               count['E'] <= target &&
               count['R'] <= target;
    }
}
