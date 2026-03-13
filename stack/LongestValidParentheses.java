import java.util.Stack;

/**
 * LeetCode 32 - Longest Valid Parentheses
 *
 * Problem:
 * Given a string containing '(' and ')',
 * return the length of the longest valid parentheses substring.
 *
 * Example:
 * Input:  ")()())"
 * Output: 4
 *
 * Approach (Stack of Indices):
 *
 * 1. Push -1 as base index.
 * 2. Traverse the string:
 *      - If '(', push index.
 *      - If ')':
 *            pop stack.
 *            If stack becomes empty → push current index.
 *            Else → calculate length.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class LongestValidParentheses {

    public static int longestValidParentheses(String s) {

        Stack<Integer> stack = new Stack<>();

        // Base for length calculation
        stack.push(-1);

        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                // Store index of '('
                stack.push(i);
            } 
            else {
                // Pop matching '('
                stack.pop();

                if (stack.isEmpty()) {
                    // No base to calculate length
                    stack.push(i);
                } 
                else {
                    // Valid substring found
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {

        String s = ")()())";

        int result = longestValidParentheses(s);

        System.out.println("Input : " + s);
        System.out.println("Longest Valid Length: " + result);
    }
}
