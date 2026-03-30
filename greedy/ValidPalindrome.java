public class ValidPalindrome {

    public static void main(String[] args) {
        Solution solution = new Solution();

        String s1 = "abca";
        String s2 = "racecar";
        String s3 = "abc";

        System.out.println(solution.validPalindrome(s1)); // true
        System.out.println(solution.validPalindrome(s2)); // true
        System.out.println(solution.validPalindrome(s3)); // false
    }
}

class Solution {

    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // Try skipping either left or right
                return isPalindrome(s, left + 1, right) ||
                       isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }

        return true;
    }

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
