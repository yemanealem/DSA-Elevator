import java.util.*;

/*
 * PROBLEM: Word Break II
 * ------------------------------------------------------
 * Given a string s and a dictionary wordDict,
 * return all possible sentences formed by inserting spaces
 * such that every word is in the dictionary.
 *
 * Example:
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat","cats","and","sand","dog"]
 *
 * Output:
 * ["cats and dog", "cat sand dog"]
 *
 * ------------------------------------------------------
 * OPTIMIZATION IDEAS:
 * 1. DFS + Backtracking
 * 2. Memoization (avoid recomputing same substring index)
 * 3. HashSet for O(1) lookup
 * 4. Limit search using max word length
 *
 * TIME COMPLEXITY:
 * Worst case: O(2^N)
 * But memoization reduces repeated work significantly.
 */

class WordBreakII {

    private Map<Integer, List<String>> memo = new HashMap<>();

    private Set<String> dict;
    private int maxWordLen = 0;

    public List<String> wordBreak(String s, List<String> wordDict) {

        dict = new HashSet<>(wordDict);

        for (String w : wordDict) {
            maxWordLen = Math.max(maxWordLen, w.length());
        }

        return dfs(s, 0);
    }

    private List<String> dfs(String s, int start) {

        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        List<String> res = new ArrayList<>();

        if (start == s.length()) {
            res.add("");
            return res;
        }

        int endLimit = Math.min(s.length(), start + maxWordLen);

        for (int end = start + 1; end <= endLimit; end++) {

            String word = s.substring(start, end);

            if (dict.contains(word)) {

                List<String> next = dfs(s, end);

                for (String sentence : next) {

                    if (sentence.isEmpty()) {
                        res.add(word);
                    } else {
                        res.add(word + " " + sentence);
                    }
                }
            }
        }

        memo.put(start, res);
        return res;
    }
    public static void main(String[] args) {

        WordBreakII solver = new WordBreakII();

        String s = "catsanddog";

        List<String> wordDict = Arrays.asList(
                "cat", "cats", "and", "sand", "dog"
        );

        List<String> result = solver.wordBreak(s, wordDict);

        System.out.println("All Possible Sentences:");

        for (String r : result) {
            System.out.println(r);
        }
    }
}