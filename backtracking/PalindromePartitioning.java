import java.util.*;

public class PalindromePartitioning {

    public static void main(String[] args) {

        String s = "aab"; // Change input here

        Solution solution = new Solution();
        List<List<String>> result = solution.partition(s);

        for (List<String> list : result) {
            System.out.println(list);
        }
    }
}

class Solution {

    public List<List<String>> partition(String s) {

        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start,
                           List<String> current,
                           List<List<String>> result) {

        // If we processed whole string → add result
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int end = start; end < s.length(); end++) {

            if (isPalindrome(s, start, end)) {

                // Choose
                current.add(s.substring(start, end + 1));

                // Explore
                backtrack(s, end + 1, current, result);

                // Backtrack
                current.remove(current.size() - 1);
            }
        }
    }

    // Optimized palindrome check
    private boolean isPalindrome(String s, int left, int right) {

        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }

        return true;
    }
}
