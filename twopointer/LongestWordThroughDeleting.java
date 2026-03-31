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
