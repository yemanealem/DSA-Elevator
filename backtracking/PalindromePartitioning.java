import java.util.*;

public class PalindromePartitioning {

    public static void main(String[] args) {

        String s = "aab";   // You can change input here

        Solution solution = new Solution();
        List<List<String>> result = solution.partition(s);

        // Print result
        for (List<String> partition : result) {
            System.out.println(partition);
        }
    }
}

class Solution {

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> current = new ArrayList<>();
        backtrack(s, 0, current, result);
        return result;
    }

    private void backtrack(String s, int start,
                           List<String> current,
                           List<List<String>> result) {

        // If we reached the end → save result
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Try every possible substring
        for (int end = start; end < s.length(); end++) {

            // If palindrome → choose it
            if (isPalindrome(s, start, end)) {

                current.add(s.substring(start, end + 1));

                // Recurse for remaining string
                backtrack(s, end + 1, current, result);

                // Backtrack (remove last choice)
                current.remove(current.size() - 1);
            }
        }
    }

    // Check if substring is palindrome
    private boolean isPalindrome(String s, int left, int right) {

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
