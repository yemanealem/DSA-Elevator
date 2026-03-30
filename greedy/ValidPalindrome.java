/**
 * Problem: Valid Palindrome II (LeetCode 680)
 *
 * Given a string s, return true if the s can be a palindrome after deleting at most one character.
 *
 * How it works (Greedy + Two Pointers):
 * - Use two pointers: left (start) and right (end)
 * - Move inward while characters match
 * - If a mismatch is found:
 *     → Try skipping the left character OR the right character
 *     → Check if either resulting substring is a palindrome
 * - If no mismatch happens → already a palindrome
 *
 * Running Time:
 * - Time Complexity: O(n)
 *   (each character is visited at most twice)
 * - Space Complexity: O(1)
 */

public class ValidPalindrome {

    public static void main(String[] args) {
        System.out.println(validPalindrome("abca"));     // true
        System.out.println(validPalindrome("racecar"));  // true
        System.out.println(validPalindrome("abc"));      // false
    }

    public static boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left + 1, right) ||
                       isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }

        return true;
    }

    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
