/*
LeetCode 3 - Longest Substring Without Repeating Characters

Problem:
Given a string s, find the length of the longest substring without repeating characters.

Example:
Input: "abcabcbb"
Output: 3
Explanation: The longest substring without repeating characters is "abc", length = 3.

Sliding Window Idea:
1. Use left and right pointers for the window.
2. Use an array lastIndex[128] to store the last index of each ASCII character.
3. If a character repeats in the window, move left to lastIndex[c] + 1.
4. Update maxLen at each step.
5. Time complexity: O(n), Space: O(128) ~ O(1)
*/

public class LongestSubstringWithoutRepeating {

    public int lengthOfLongestSubstring(String s) {
        int[] lastIndex = new int[128]; // ASCII
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            // Move left pointer if current character repeats
            left = Math.max(left, lastIndex[c]);
            maxLen = Math.max(maxLen, right - left + 1);
            // Store next index of current character
            lastIndex[c] = right + 1;
        }

        return maxLen;
    }

    // Main method to test
    public static void main(String[] args) {
        LongestSubstringWithoutRepeating sol = new LongestSubstringWithoutRepeating();

        System.out.println(sol.lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(sol.lengthOfLongestSubstring("bbbbb"));    // 1
        System.out.println(sol.lengthOfLongestSubstring("pwwkew"));   // 3
        System.out.println(sol.lengthOfLongestSubstring(""));         // 0
        System.out.println(sol.lengthOfLongestSubstring("au"));       // 2
        System.out.println(sol.lengthOfLongestSubstring("dvdf"));     // 3
    }
}
