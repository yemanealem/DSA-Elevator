
/*
 * LeetCode 316 - Remove Duplicate Letters
 *
 * Question:
 * Given a string, remove duplicate letters so that every letter appears
 * once and the result is the smallest lexicographical order.
 *
 * How it Works:
 * - Track last occurrence of each character.
 * - Use StringBuilder as a stack (faster than Deque<Character>).
 * - If current character is smaller than stack top
 *   AND the top appears later, pop it.
 * - Use visited[] to avoid duplicates.
 *
 * Runtime:
 * Time Complexity: O(n)
 * Space Complexity: O(1) (only 26 lowercase letters)
 */

public class RemoveDuplicateLetters {

    public static String removeDuplicateLetters(String s) {

        int n = s.length();
        int[] last = new int[26];
        boolean[] visited = new boolean[26];

        // Record last occurrence of each character
        for (int i = 0; i < n; i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        StringBuilder stack = new StringBuilder();

        for (int i = 0; i < n; i++) {

            char c = s.charAt(i);

            if (visited[c - 'a']) {
                continue;
            }

            // Maintain lexicographically smallest order
            while (stack.length() > 0 &&
                   c < stack.charAt(stack.length() - 1) &&
                   last[stack.charAt(stack.length() - 1) - 'a'] > i) {

                visited[stack.charAt(stack.length() - 1) - 'a'] = false;
                stack.deleteCharAt(stack.length() - 1);
            }

            stack.append(c);
            visited[c - 'a'] = true;
        }

        return stack.toString();
    }

    public static void main(String[] args) {

        System.out.println(removeDuplicateLetters("bcabc"));     // abc
        System.out.println(removeDuplicateLetters("cbacdcbc"));  // acdb
        System.out.println(removeDuplicateLetters("abacb"));      // abc
        System.out.println(removeDuplicateLetters("bbcaac"));     // bac
    }
}
