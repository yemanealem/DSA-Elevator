import java.util.*;

public class LongestWordThroughDeleting {

    public static String findLongestWord(String s, List<String> dictionary) {
        // Sort by length DESC, then lexicographically ASC
        Collections.sort(dictionary, (a, b) -> {
            if (a.length() != b.length()) {
                return b.length() - a.length();
            }
            return a.compareTo(b);
        });

        for (String word : dictionary) {
            if (isSubsequence(s, word)) {
                return word; // first valid is best due to sorting
            }
        }

        return "";
    }

    // Two-pointer subsequence check
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

        System.out.println(findLongestWord(s, dictionary)); // apple
    }
}
