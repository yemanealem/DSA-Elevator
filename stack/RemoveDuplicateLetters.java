import java.util.*;

/**
 * LeetCode 316 - Remove Duplicate Letters
 *
 * Problem:
 * Remove duplicate letters so that every letter appears once
 * and result is the smallest lexicographical order.
 *
 * Approach:
 * 1. Track last occurrence of each character.
 * 2. Use stack (Deque) to build result.
 * 3. If current char is smaller than stack top
 *    AND stack top appears later, pop it.
 * 4. Use visited[] to avoid duplicates.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1) (only 26 lowercase letters)
 */

public class RemoveDuplicateLetters {

    public static String removeDuplicateLetters(String s) {

        int[] lastIndex = new int[26];
        boolean[] visited = new boolean[26];

        // Record last index of each character
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {

            char current = s.charAt(i);

            // If already used, skip
            if (visited[current - 'a']) {
                continue;
            }

            // Maintain lexicographical order
            while (!stack.isEmpty() &&
                    current < stack.peekLast() &&
                    lastIndex[stack.peekLast() - 'a'] > i) {

                visited[stack.pollLast() - 'a'] = false;
            }

            stack.offerLast(current);
            visited[current - 'a'] = true;
        }

        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }

        return result.toString();
    }

    public static void main(String[] args) {

        System.out.println(removeDuplicateLetters("bcabc"));    // abc
        System.out.println(removeDuplicateLetters("cbacdcbc")); // acdb
        System.out.println(removeDuplicateLetters("abacb"));     // abc
        System.out.println(removeDuplicateLetters("bbcaac"));    // bac
    }
}
