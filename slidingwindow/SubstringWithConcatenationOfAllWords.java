import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenationOfAllWords {

    /*
    ============================================================
    LeetCode 30. Substring with Concatenation of All Words
    ============================================================

    Problem:
    --------
    Given a string s and an array of strings words.
    All words have the same length.

    Return all starting indices of substring(s) in s that is a
    concatenation of each word exactly once and without
    any intervening characters.

    Example:
    --------
    Input:
        s = "barfoothefoobarman"
        words = ["foo","bar"]

    Output:
        [0, 9]

    ============================================================
    SLIDING WINDOW APPROACH
    ============================================================

    Key Idea:
    ----------
    1. Each word has equal length → wordLen
    2. Total window size = wordLen * words.length
    3. Move window in steps of wordLen
    4. Use two HashMaps:
         - wordCount (required frequency)
         - currentMap (current window frequency)

    We iterate starting from 0 to wordLen - 1
    to handle different alignments.

    ============================================================
    RUNNING TIME
    ============================================================

    Let:
        n = s.length()
        m = number of words
        k = word length

    Each character is processed at most twice.
    Time Complexity: O(n)
    (More precisely O(n * k) due to substring creation)

    Space Complexity: O(m)
    For storing word frequencies.

    ============================================================
    */

    public static List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words.length == 0)
            return result;

        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        int wordLen = words[0].length();
        int totalWords = words.length;

        for (int i = 0; i < wordLen; i++) {

            int left = i;
            int count = 0;
            Map<String, Integer> currentMap = new HashMap<>();

            for (int right = i; right + wordLen <= s.length(); right += wordLen) {

                String word = s.substring(right, right + wordLen);

                if (wordCount.containsKey(word)) {

                    currentMap.put(word,
                            currentMap.getOrDefault(word, 0) + 1);
                    count++;

                    while (currentMap.get(word) >
                            wordCount.get(word)) {

                        String leftWord =
                                s.substring(left, left + wordLen);

                        currentMap.put(leftWord,
                                currentMap.get(leftWord) - 1);

                        left += wordLen;
                        count--;
                    }

                    if (count == totalWords) {
                        result.add(left);
                    }

                } else {
                    currentMap.clear();
                    count = 0;
                    left = right + wordLen;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};

        System.out.println(findSubstring(s, words)); // [0, 9]
    }
}
