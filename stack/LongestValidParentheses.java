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
 * Optimized Approach:
 * - Use Deque as stack (faster than Stack class).
 * - Store indices instead of characters.
 * - Use -1 as base index for length calculation.
 *
 * Time Complexity:  O(n)
 * Space Complexity: O(n)
 */

public class LongestValidParentheses {

    public static int longestValidParentheses(String s) {

        Deque<Integer> stack = new ArrayDeque<>();

        // Base index to calculate lengths
        stack.push(-1);

        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                // Push index of '('
                stack.push(i);
            } 
            else {
                // Pop matching '('
                stack.pop();

                if (stack.isEmpty()) {
                    // Start new base
                    stack.push(i);
                } 
                else {
                    // Calculate valid substring length
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {

        String input = ")()())";

        int result = longestValidParentheses(input);

        System.out.println("Input: " + input);
        System.out.println("Longest Valid Parentheses Length: " + result);
    }
}
