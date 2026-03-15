import java.util.*;

/*
------------------------------------------------------------
LeetCode Problem: Palindrome Partitioning (Medium)

Given a string s, partition it such that every substring
in the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:
Input:  "aab"

Output:
[
  ["a","a","b"],
  ["aa","b"]
]

------------------------------------------------------------
How It Works (Backtracking Approach):

1. Start from index 0.
2. Try every possible substring from current index.
3. If the substring is a palindrome:
      - Add it to the current path.
      - Recursively solve for remaining string.
4. After recursion, remove the last choice (backtracking).
5. When we reach the end of string,
   we add the current partition to result.

This explores ALL possible valid palindrome partitions.

Time Complexity: O(n * 2^n)
Space Complexity: O(n)
------------------------------------------------------------
*/

public class PalindromePartitioning {

    public static void main(String[] args) {

        String s = "aab";  // You can change input here

        Solution solution = new Solution();
        List<List<String>> result = solution.partition(s);

        // Print all partitions
        for (List<String> partition : result) {
            System.out.println(partition);
        }
    }
}

class Solution {

    public List<List<String>> partition(String s) {

        List<List<String>> result = new ArrayList<>();

        // Start backtracking from index 0
        backtrack(s, 0, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(String s, int start,
                           List<String> current,
                           List<List<String>> result) {

        // Base case: if we reached end of string
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Try all possible substrings starting from 'start'
        for (int end = start; end < s.length(); end++) {

            // Check if substring is palindrome
            if (isPalindrome(s, start, end)) {

                // Choose
                current.add(s.substring(start, end + 1));

                // Explore
                backtrack(s, end + 1, current, result);

                // Backtrack (undo choice)
                current.remove(current.size() - 1);
            }
        }
    }

    // Helper method to check palindrome
    private boolean isPalindrome(String s, int left, int right) {

        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }

        return true;
    }
}
