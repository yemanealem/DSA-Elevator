import java.util.Stack;

public class RemoveKDigits {

    /*
     * LeetCode Problem: Remove K Digits
     *
     * Question:
     * Given a string num representing a non-negative integer and an integer k,
     * remove k digits from the number so that the new number is the smallest possible.
     *
     * Example:
     * Input: num = "1432219", k = 3
     * Output: "1219"
     *
     * How It Works (Greedy + Monotonic Stack):
     * 1. Use a stack to maintain digits in increasing order.
     * 2. While:
     *      - k > 0
     *      - stack not empty
     *      - top of stack > current digit
     *    → pop the stack (remove larger digit).
     *
     * 3. Push current digit into stack.
     * 4. If k still > 0 after traversal, remove remaining digits from the end.
     * 5. Build result and remove leading zeros.
     *
     * Running Time:
     * Time Complexity: O(n)
     *   - Each digit pushed and popped at most once.
     * Space Complexity: O(n)
     *   - Stack stores digits.
     */

    public static String removeKdigits(String num, int k) {

        Stack<Character> stack = new Stack<>();

        for (char digit : num.toCharArray()) {

            while (k > 0 && !stack.isEmpty() && stack.peek() > digit) {
                stack.pop();
                k--;
            }

            stack.push(digit);
        }

        // If k still remains, remove from end
        while (k > 0) {
            stack.pop();
            k--;
        }

        // Build result
        StringBuilder result = new StringBuilder();
        for (char digit : stack) {
            result.append(digit);
        }

        // Remove leading zeros
        while (result.length() > 0 && result.charAt(0) == '0') {
            result.deleteCharAt(0);
        }

        return result.length() == 0 ? "0" : result.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeKdigits("1432219", 3)); // Output: 1219
        System.out.println(removeKdigits("10200", 1));   // Output: 200
        System.out.println(removeKdigits("10", 2));      // Output: 0
    }
}
