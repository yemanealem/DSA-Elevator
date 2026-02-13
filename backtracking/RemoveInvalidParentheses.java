/*
LeetCode Problem: Remove Invalid Parentheses

Given a string s that contains parentheses and letters,
remove the minimum number of invalid parentheses to make the input string valid.

Return all possible valid results.

A string is valid if:
1. Open brackets must be closed by the same type.
2. Open brackets must be closed in the correct order.

Example 1:
Input: s = "()())()"
Output: ["(())()", "()()()"]

Example 2:
Input: s = "(a)())()"
Output: ["(a())()", "(a)()()"]

Example 3:
Input: s = ")("
Output: [""]

--------------------------------------------------------

🔥 OPTIMIZED TWO-PASS DFS SOLUTION

Idea:
1) Scan from left → right
   Remove extra ')'
2) Reverse string
   Scan again to remove extra '('

This avoids:
- BFS
- HashSet
- Duplicate states
- Heavy memory usage

Time Complexity: O(2^n) worst case
But heavily pruned → very fast in practice
*/

import java.util.*;

public class RemoveInvalidParentheses {

    public static List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        remove(s, result, 0, 0, new char[]{'(', ')'});
        return result;
    }

    private static void remove(String s,
                               List<String> result,
                               int last_i,
                               int last_j,
                               char[] par) {

        int stack = 0;

        for (int i = last_i; i < s.length(); i++) {

            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;

            // If valid so far, continue
            if (stack >= 0) continue;

            /*
            🔥 We found extra closing parenthesis

            Example:
            s = "()())()"
                     ^
                     stack becomes negative here

            Now we try removing one ')'
            between last_j and current i
            */

            for (int j = last_j; j <= i; j++) {

                // Remove only first ')' in consecutive duplicates
                if (s.charAt(j) == par[1] &&
                   (j == last_j || s.charAt(j - 1) != par[1])) {

                    remove(
                        s.substring(0, j) + s.substring(j + 1),
                        result,
                        i,
                        j,
                        par
                    );
                }
            }
            return; // stop here (important pruning)
        }

        /*
        If we reach here:
        No extra closing parenthesis found.

        Now reverse string and check for extra '('
        */

        String reversed = new StringBuilder(s).reverse().toString();

        if (par[0] == '(') {
            // Second pass to remove extra '('
            remove(reversed, result, 0, 0, new char[]{')', '('});
        } else {
            // Both passes done → valid result
            result.add(reversed);
        }
    }

    // MAIN METHOD FOR TESTING
    public static void main(String[] args) {

        String s = "()())()";

        List<String> result = removeInvalidParentheses(s);

        System.out.println("Valid combinations:");
        for (String str : result) {
            System.out.println(str);
        }
    }
}
