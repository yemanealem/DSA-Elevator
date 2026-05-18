/*
    LeetCode: Palindrome Pairs

    QUESTION:
    Given a list of unique words, return all pairs of indices (i, j)
    such that words[i] + words[j] forms a palindrome.

    Example:
    Input:  ["bat","tab","cat"]
    Output: [[1,0],[0,1]]


    --------------------------------------------------
    HOW THIS SOLUTION WORKS
    --------------------------------------------------

    IDEA:
    For each word, we try splitting it into two parts:

        prefix | suffix

    Then we check two cases:

    --------------------------------------------------

    CASE 1:
    If prefix is a palindrome,
    then we need a word that matches reverse(suffix)

    Why?
    Because:
        prefix + suffix
    If prefix is already palindrome, suffix must be mirrored
    on the other side.

    --------------------------------------------------

    CASE 2:
    If suffix is a palindrome,
    then we need a word that matches reverse(prefix)

    Why?
    Because:
        prefix + suffix
    If suffix is palindrome, prefix must be mirrored before it.

    --------------------------------------------------

    We use a HashMap:
        word -> index

    So we can quickly check if reverse exists in O(1).

    --------------------------------------------------
    TIME COMPLEXITY
    --------------------------------------------------

    Let:
        n = number of words
        k = average word length

    For each word:
        we try k splits

    Each split:
        substring + palindrome check + reverse

    Total:
        O(n * k^2)

    --------------------------------------------------
    SPACE COMPLEXITY
    --------------------------------------------------

    HashMap storage:
        O(n)

    Result storage:
        O(answer pairs)

    --------------------------------------------------
*/

import java.util.*;

public class PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {

        List<List<Integer>> result = new ArrayList<>();

        Map<String, Integer> map = new HashMap<>();

        // store words with index
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {

            String word = words[i];

            for (int j = 0; j <= word.length(); j++) {

                String prefix = word.substring(0, j);
                String suffix = word.substring(j);

                // CASE 1: prefix is palindrome
                if (isPalindrome(prefix)) {

                    String revSuffix = new StringBuilder(suffix)
                            .reverse()
                            .toString();

                    if (map.containsKey(revSuffix) && map.get(revSuffix) != i) {
                        result.add(Arrays.asList(map.get(revSuffix), i));
                    }
                }

                // CASE 2: suffix is palindrome
                if (j != word.length() && isPalindrome(suffix)) {

                    String revPrefix = new StringBuilder(prefix)
                            .reverse()
                            .toString();

                    if (map.containsKey(revPrefix) && map.get(revPrefix) != i) {
                        result.add(Arrays.asList(i, map.get(revPrefix)));
                    }
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String s) {

        int l = 0, r = s.length() - 1;

        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        PalindromePairs solution = new PalindromePairs();

        String[] words = {"bat", "tab", "cat"};

        List<List<Integer>> result = solution.palindromePairs(words);

        System.out.println(result);
    }
}