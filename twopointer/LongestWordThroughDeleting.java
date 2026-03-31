/*
QUESTION:
Given a string s and a dictionary of strings, find the longest string in the dictionary that can be formed by deleting some characters of s (without changing the order of the remaining characters).
If there are multiple valid answers, return the lexicographically smallest one.

HOW IT WORKS:
- For each word in the dictionary:
  - Use two pointers to check if it is a subsequence of s.
  - Pointer i scans s, pointer j scans the word.
  - If characters match, move j forward.
  - If we successfully match all characters of the word, it is a valid candidate.
- Keep track of the best result:
  - Prefer longer words.
  - If lengths are equal, choose lexicographically smaller.

RUNNING TIME:
- Let n = length of s, m = number of words, k = average word length
- For each word, subsequence check takes O(n)
- Total time complexity: O(m × n)
- Space complexity: O(1)
*/

import java.util.*;

public class LongestWordThroughDeleting {

    public static String findLongestWord(String s, List<String> dictionary) {
        String result = "";

        for (String word : dictionary) {
            if (isSubsequence(s, word)) {
                // Choose better candidate
                if (word.length() > result.length() ||
                   (word.length() == result.length() && word.compareTo(result) < 0)) {
                    result = word;
                }
            }
        }

        return result;
    }

    // Two-pointer check for subsequence
    private static boolean isSubsequence(String s, String word) {
        int i = 0, j = 0;

        while (i < s.length() && j < word.length()) {
            if (s.charAt(i) == word.charAt(j)) {
                j++;
            }
            i++;
        }

        return j == word.length();
    }

    public static void main(String[] args) {
        String s = "abpcplea";
        List<String> dictionary = Arrays.asList("ale", "apple", "monkey", "plea");

        System.out.println(findLongestWord(s, dictionary)); // Output: "apple"
    }
}
