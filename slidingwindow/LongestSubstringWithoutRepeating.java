/*
LeetCode 3 - Longest Substring Without Repeating Characters

Problem:
Given a string s, find the length of the longest substring without repeating characters.

Example:
Input: "abcabcbb"
Output: 3
Explanation: The longest substring without repeating characters is "abc", length = 3.

Sliding Window Idea:
1. Use left and right pointers to represent the window of non-repeating characters.
2. Use an array lastIndex[128] to store the next index to move left to if a duplicate is found.
3. If a character repeats, move left pointer to lastIndex[c].
4. Update maxLen = max(maxLen, right - left + 1) at each step.

Trace Example for s = "abcabcbb":

Step-by-step:

right=0, char='a', lastIndex['a']=0, left=0
Window: "a", maxLen=1
Update lastIndex['a'] = 1

right=1, char='b', lastIndex['b']=0, left=0
Window: "ab", maxLen=2
Update lastIndex['b'] = 2

right=2, char='c', lastIndex['c']=0, left=0
Window: "abc", maxLen=3
Update lastIndex['c'] = 3

right=3, char='a', lastIndex['a']=1, left=max(0,1)=1
Window: "bca", maxLen=3
Update lastIndex['a'] = 4

right=4, char='b', lastIndex['b']=2, left=max(1,2)=2
Window: "cab", maxLen=3
Update lastIndex['b'] = 5

right=5, char='c', lastIndex['c']=3, left=max(2,3)=3
Window: "abc", maxLen=3
Update lastIndex['c'] = 6

right=6, char='b', lastIndex['b']=5, left=max(3,5)=5
Window: "cb", maxLen=3
Update lastIndex['b'] = 7

right=7, char='b', lastIndex['b']=7, left=max(5,7)=7
Window: "b", maxLen=3
Update lastIndex['b'] = 8

Final Answer: maxLen = 3 ("abc")
*/

public class LongestSubstringWithoutRepeating {

    public int lengthOfLongestSubstring(String s) {
        int[] lastIndex = new int[128]; // stores next index to move left for ASCII chars
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            // Move left pointer if duplicate character found
            left = Math.max(left, lastIndex[c]);
            maxLen = Math.max(maxLen, right - left + 1);
            // Update next index for current char
            lastIndex[c] = right + 1;
        }

        return maxLen;
    }

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
