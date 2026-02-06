/*
LeetCode 424 - Longest Repeating Character Replacement

Problem:
Given a string s consisting of uppercase letters and an integer k, 
you can replace at most k characters in s to get the longest substring 
with all repeating characters. Return the length of that substring.

Example:
Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace one 'A' in the middle to get "AABBBBA" or "AAAABBA".
The longest repeating substring after replacement is "BBBB" or "AAAA", length = 4.

How it works (Sliding Window Approach):
1. Use a window [left, right] to represent the current substring.
2. Track the count of each character in the window.
3. Track maxCount = max frequency of a single character in the window.
4. If (window length - maxCount) > k, the window is invalid, shrink from left.
5. Otherwise, update maxLen = max(maxLen, window length).
6. Slide right pointer through the string, shrinking left when needed.
7. Return maxLen.

Trace Example for s = "AABABBA", k = 1:

Step-by-step:
Window: "A"         left=0, right=0, maxCount=1, maxLen=1
Window: "AA"        left=0, right=1, maxCount=2, maxLen=2
Window: "AAB"       left=0, right=2, maxCount=2, maxLen=3
Window: "AABA"      left=0, right=3, maxCount=2, maxLen=4
Window: "AABAB"     left=1, right=4, maxCount=3, maxLen=4 (shrink left)
Window: "ABAB"      left=2, right=4, maxCount=2, maxLen=4
Window: "ABABB"     left=2, right=5, maxCount=3, maxLen=4
Window: "ABABBA"    left=3, right=6, maxCount=3, maxLen=4

Final Answer: 4
*/

public class CharacterReplacement {

    public int characterReplacement(String s, int k) {
        int[] count = new int[26];  // Frequency array for 'A'-'Z'
        int left = 0;               // Left pointer of the window
        int maxCount = 0;           // Max frequency in current window
        int maxLen = 0;             // Result

        for (int right = 0; right < s.length(); right++) {
            int idx = s.charAt(right) - 'A';
            count[idx]++;
            maxCount = Math.max(maxCount, count[idx]); // Update max char frequency

            // Shrink window if replacements needed exceed k
            while ((right - left + 1) - maxCount > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1); // Update max length
        }

        return maxLen;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        CharacterReplacement sol = new CharacterReplacement();

        System.out.println(sol.characterReplacement("ABAB", 2));    // Output: 4
        System.out.println(sol.characterReplacement("AABABBA", 1)); // Output: 4
        System.out.println(sol.characterReplacement("AAAA", 0));    // Output: 4
        System.out.println(sol.characterReplacement("ABCDE", 2));   // Output: 3
        System.out.println(sol.characterReplacement("BAAAB", 2));   // Output: 5
    }
}
