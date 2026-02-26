import java.util.ArrayDeque;
import java.util.Deque;

/*
 * LeetCode 394 - Decode String
 *
 * Question:
 * Decode an encoded string where:
 * k[encoded] -> repeat encoded k times.
 *
 * Example:
 * "3[a2[c]]" -> "accaccacc"
 *
 * How It Works:
 * - Use two stacks: one for strings, one for numbers.
 * - When ']' is found:
 *     - Pop string stack (inner content)
 *     - Pop number stack (repeat count)
 *     - Build result and push back to string stack
 *
 * Runtime:
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class DecodeString {

    public static String decodeString(String s) {

        Deque<String> stringStack = new ArrayDeque<>();
        Deque<Integer> countStack = new ArrayDeque<>();

        StringBuilder current = new StringBuilder();
        int number = 0;

        for (char c : s.toCharArray()) {

            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '[') {
                countStack.offerLast(number);
                stringStack.offerLast(current.toString());
                current.setLength(0);
                number = 0;
            } else if (c == ']') {

                int repeat = countStack.pollLast();
                StringBuilder temp = new StringBuilder(stringStack.pollLast());

                String str = current.toString();
                for (int i = 0; i < repeat; i++) {
                    temp.append(str);
                }

                current = temp;

            } else {
                current.append(c);
            }
        }

        return current.toString();
    }

    public static void main(String[] args) {

        System.out.println(decodeString("3[a2[c]]"));       // accaccacc
        System.out.println(decodeString("2[abc]3[cd]ef"));   // abcabccdcdcdef
        System.out.println(decodeString("3[a]2[bc]"));       // aaabcbc
        System.out.println(decodeString("10[a]"));           // aaaaaaaaaa
    }
}
