import java.util.*;

public class Solution {
    public int balancedString(String s) {
        int n = s.length();
        int target = n / 4;

        Map<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // If already balanced
        if (isBalanced(count, target)) return 0;

        int left = 0;
        int minLen = n;

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);
            count.put(c, count.get(c) - 1);

            // Try shrinking window
            while (left <= right && isBalanced(count, target)) {
                minLen = Math.min(minLen, right - left + 1);
                char leftChar = s.charAt(left);
                count.put(leftChar, count.get(leftChar) + 1);
                left++;
            }
        }

        return minLen;
    }

    private boolean isBalanced(Map<Character, Integer> count, int target) {
        return count.getOrDefault('Q', 0) <= target &&
               count.getOrDefault('W', 0) <= target &&
               count.getOrDefault('E', 0) <= target &&
               count.getOrDefault('R', 0) <= target;
    }
}
