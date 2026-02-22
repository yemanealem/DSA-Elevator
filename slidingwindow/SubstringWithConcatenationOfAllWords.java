public class SubstringWithConcatenationOfAllWords {

    /*
    ============================================================
    LeetCode 30. Substring with Concatenation of All Words
    ============================================================

    Problem:
    --------
    You are given a string s and an array of strings words.
    All words are of the same length.

    A concatenated substring is formed by concatenating each word
    exactly once in any order, without any extra characters.

    Return all starting indices of substring(s) in s that is a
    concatenation of each word exactly once.

    ------------------------------------------------------------
    Example:
    ------------------------------------------------------------
    Input:
        s = "barfoothefoobarman"
        words = ["foo","bar"]

    Output:
        [0, 9]

    Explanation:
        "barfoo" starts at index 0
        "foobar" starts at index 9

    ============================================================
    SLIDING WINDOW APPROACH
    ============================================================

    Key Observations:
    -----------------
    1. All words have same length → wordLen
    2. Total window size = wordLen * numberOfWords
    3. We move window in steps of wordLen
    4. Use frequency map to track required words

    Algorithm:
    ----------
    1. Build a frequency map of words.
    2. Loop from i = 0 to wordLen - 1
       (to handle different alignments)
    3. Use sliding window:
        - right pointer moves in steps of wordLen
        - track current word count
        - if word exceeds frequency → shrink from left
        - if count matches → record starting index

    ============================================================
    TRACE EXAMPLE
    ============================================================

    s = "barfoothefoobarman"
    words = ["foo","bar"]

    wordLen = 3
    totalWords = 2
    windowSize = 6

    Start i = 0

    right=0 → "bar" ✓
    right=3 → "foo" ✓
    count=2 → add index 0

    right=9 → "foo"
    right=12 → "bar"
    count=2 → add index 9

    Result = [0,9]

    ============================================================
    Time Complexity:
    ------------------------------------------------------------
    n = length of string
    m = number of words
    k = word length

    Each character is visited at most twice.
    Time Complexity = O(n)
    (more precisely O(n * k))

    ============================================================
    Space Complexity:
    ------------------------------------------------------------
    O(m) for storing word frequencies
    ============================================================
    */

    public static java.util.List<Integer> findSubstring(String s, String[] words) {

        java.util.List<Integer> result = new java.util.ArrayList<>();
        if (s == null || s.length() == 0 || words.length == 0)
            return result;

        java.util.Map<String, Integer> wordCount = new java.util.HashMap<>();

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        int wordLen = words[0].length();
        int totalWords = words.length;
        int windowSize = wordLen * totalWords;

        for (int i = 0; i < wordLen; i++) {

            int left = i;
            int count = 0;
            java.util.Map<String, Integer> currentMap = new java.util.HashMap<>();

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
