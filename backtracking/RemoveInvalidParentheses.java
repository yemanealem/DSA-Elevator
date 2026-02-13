/*
LeetCode: Remove Invalid Parentheses

Return all possible results after removing
the minimum number of invalid parentheses.

Time Complexity: O(2^n)
Space Complexity: O(n)
*/

import java.util.*;

class Solution {

    public List<String> removeInvalidParentheses(String s) {
        Set<String> result = new HashSet<>();
        
        int leftRemove = 0, rightRemove = 0;

        // Step 1: Count how many parentheses we must remove
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRemove++;
            } else if (c == ')') {
                if (leftRemove == 0) {
                    rightRemove++;
                } else {
                    leftRemove--;
                }
            }
        }

        backtrack(s, 0, 0, 0, leftRemove, rightRemove, new StringBuilder(), result);
        
        return new ArrayList<>(result);
    }

    private void backtrack(String s, int index,
                           int leftCount, int rightCount,
                           int leftRemove, int rightRemove,
                           StringBuilder path,
                           Set<String> result) {

        // Base case
        if (index == s.length()) {
            if (leftRemove == 0 && rightRemove == 0) {
                result.add(path.toString());
            }
            return;
        }

        char c = s.charAt(index);
        int length = path.length();

        // OPTION 1: Remove current parenthesis
        if (c == '(' && leftRemove > 0) {
            backtrack(s, index + 1, leftCount, rightCount,
                      leftRemove - 1, rightRemove, path, result);
        }

        if (c == ')' && rightRemove > 0) {
            backtrack(s, index + 1, leftCount, rightCount,
                      leftRemove, rightRemove - 1, path, result);
        }

        // OPTION 2: Keep character
        path.append(c);

        if (c != '(' && c != ')') {
            backtrack(s, index + 1, leftCount, rightCount,
                      leftRemove, rightRemove, path, result);
        } 
        else if (c == '(') {
            backtrack(s, index + 1, leftCount + 1, rightCount,
                      leftRemove, rightRemove, path, result);
        } 
        else if (rightCount < leftCount) {
            backtrack(s, index + 1, leftCount, rightCount + 1,
                      leftRemove, rightRemove, path, result);
        }

        // Backtrack
        path.deleteCharAt(length);
    }
}
