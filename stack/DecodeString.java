import java.util.*;

/*
 * LeetCode 394 - Decode String
 *
 * Question:
 * Given an encoded string, return its decoded string.
 *
 * Encoding rule:
 * k[encoded_string] means repeat encoded_string k times.
 *
 * Example:
 * Input: "3[a2[c]]"
 * Output: "accaccacc"
 *
 * How It Works (Stack Approach):
 * - Use stack to process characters.
 * - When ']' is found, pop until '[' to get substring.
 * - Also pop digits to get repeat count.
 * - Build repeated string and push back to stack.
 *
 * Runtime:
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class DecodeString {

    public static String decodeString(String s) {

        Deque<String> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c != ']') {
                stack.offerLast(String.valueOf(c));
            } else {

                // Build encoded string inside []
                StringBuilder sb = new StringBuilder();

                while (!stack.isEmpty() && !stack.peekLast().equals("[")) {
                    sb.insert(0, stack.pollLast());
                }

                // Remove '['
                stack.pollLast();

                // Get number (repeat count)
                StringBuilder num = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peekLast().charAt(0))) {
                    num.insert(0, stack.pollLast());
                }

                int repeat = Integer.parseInt(num.toString());

                // Repeat decoded string
                String decoded = sb.toString().repeat(repeat);

                stack.offerLast(decoded);
            }
        }

        // Build final result
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pollLast());
        }

        return result.toString();
    }

    public static void main(String[] args) {

        System.out.println(decodeString("3[a2[c]]"));  // accaccacc
        System.out.println(decodeString("2[abc]3[cd]ef")); // abcabccdcdcdef
        System.out.println(decodeString("3[a]2[bc]")); // aaabcbc
    }
}
