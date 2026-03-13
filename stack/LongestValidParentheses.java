import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 32 - Longest Valid Parentheses
 *
 * Problem:
 * Given a string containing only '(' and ')',
 * return the length of the longest valid parentheses substring.
 *
 * Example:
 * Input:  ")()())"
 * Output: 4
 *
 * Enhanced Stack Approach:
 * - Use Deque instead of Stack (faster and recommended).
 * - Store indices, not characters.
 * - Push -1 as base index.
 *
 * Why -1?
 * It helps calculate the length of valid substrings correctly.
 *
 * Algorithm:
 * 1. Initialize stack with -1.
 * 2. Traverse string:
 *      - If '(' → push index.
 *      - If ')':
 *            pop stack.
 *            If stack becomes empty → push current index.
 *            Else → update max length.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class LongestValidParentheses {

    public static int longestValidParentheses(String s) {

        Deque<Integer> stack = new ArrayDeque<>();

        // Base index for correct length calculation
        stack.push(-1);

        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {

                // Store index of '('
                stack.push(i);

            } else {

                // Remove matching '('
                stack.pop();

                if (stack.isEmpty()) {

                    // No base to calculate length
                    // Start new invalid boundary
                    stack.push(i);

                } else {

                    // Valid substring found
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {

        String input = ")()())";

        System.out.println("Input: " + input);
        System.out.println("Longest Valid Length: " + longestValidParentheses(input));
    }
}
