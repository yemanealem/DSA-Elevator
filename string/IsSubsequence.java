public class IsSubsequence {

    public boolean isSubsequence(String s, String t) {
        int i = 0, n = s.length();
        int m = t.length();

        if (n > m) return false; 

        for (int j = 0; j < m && i < n; j++) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
        }

        return i == n;
    }

    // Main method for testing
    public static void main(String[] args) {
        IsSubsequence solution = new IsSubsequence();

        // Test cases
        String s1 = "abc";
        String t1 = "ahbgdc";

        String s2 = "axc";
        String t2 = "ahbgdc";

        System.out.println(solution.isSubsequence(s1, t1)); // true
        System.out.println(solution.isSubsequence(s2, t2)); // false
    }
}
