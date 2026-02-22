import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenationOfAllWords {

    /*
    ============================================================
    LeetCode 30. Substring with Concatenation of All Words
    ============================================================

    PROBLEM:
    --------
    Given a string s and an array of strings words.
    All words have the same length.

    Return all starting indices of substring(s) in s that is a
    concatenation of each word exactly once and without any
    intervening characters.

    ------------------------------------------------------------
    Example:
    ------------------------------------------------------------
    Input:
        s = "barfoothefoobarman"
        words = ["foo","bar"]

    Output:
        [0, 9]

    Explanation:
        "barfoo" starting at index 0
        "foobar" starting at index 9

    ============================================================
    HOW IT WORKS (Sliding Window)
    ============================================================

    Key Observations:
    1. All words have equal length → wordLen
    2. Total window size = wordLen * number of words
    3. We move window in steps of wordLen
    4. We must handle different starting alignments:
       (0, 1, ..., wordLen-1)

    Steps:
    ------
    1. Build a frequency map (target) of required words.
    2. For each offset from 0 to wordLen-1:
        - Use two pointers (left, right)
        - Move right in steps of wordLen
        - Track current window frequencies
        - If a word exceeds required frequency:
              shrink window from left
        - If count == totalWords:
              record left index

    This avoids checking every substring blindly.

    ============================================================
    TRACE EXAMPLE
    ============================================================

    s = "barfoothefoobarman"
    words = ["foo","bar"]

    wordLen = 3
    totalWords = 2
    windowSize = 6

    offset = 0

    right=0 → "bar" ✓
    right=3 → "foo" ✓
    count=2 → add index 0

    right=9 → "foo"
    right=12 → "bar"
    count=2 → add index 9

    Result = [0, 9]

    ============================================================
    RUNNING TIME ANALYSIS
    ============================================================

    Let:
        n = s.length()
        m = number of words
        k = word length

    Outer loop runs k times.
    Inner loop processes each word-sized chunk once.

    Each character is visited at most twice
    (once by right pointer, once by left pointer).

    Therefore:

        Time Complexity = O(n)

    (More precisely O(n * k) due to substring creation.)

    ============================================================
    SPACE COMPLEXITY
    ============================================================

    We use:
        - target map (size m)
        - window map (size ≤ m)

        Space Complexity = O(m)

    ============================================================
    */

    public static List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words.length == 0)
            return result;

        int wordLen = words[0].length();
        int totalWords = words.length;
        int n = s.length();

        Map<String, Integer> target = new HashMap<>();
        for (String w : words)
            target.put(w, target.getOrDefault(w, 0) + 1);

        for (int offset = 0; offset < wordLen; offset++) {

            int left = offset;
            int count = 0;
            Map<String, Integer> window = new HashMap<>();

            for (int right = offset; right + wordLen <= n; right += wordLen) {

                String word = s.substring(right, right + wordLen);
                Integer freq = target.get(word);

                if (freq != null) {

                    window.put(word, window.getOrDefault(word, 0) + 1);
                    count++;

                    while (window.get(word) > freq) {
                        String leftWord = s.substring(left, left + wordLen);
                        window.put(leftWord, window.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    if (count == totalWords) {
                        result.add(left);
                    }

                } else {
                    window.clear();
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
