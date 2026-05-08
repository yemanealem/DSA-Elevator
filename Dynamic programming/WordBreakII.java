import java.util.*;

/*
 * PROBLEM: Word Break II
 * ----------------------------------------------------
 * Given a string s and a dictionary of words wordDict,
 * return all possible sentences where:
 *
 * - Each word is valid in the dictionary
 * - Spaces are inserted correctly
 *
 * Example:
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat","cats","and","sand","dog"]
 *
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 *
 * APPROACH:
 * ----------------------------------------------------
 * 1. Use DFS + Backtracking
 * 2. Try every possible prefix
 * 3. If prefix exists in dictionary:
 *      recursively solve remaining substring
 * 4. Use Memoization (DP cache)
 *      to avoid repeated calculations
 *
 * TIME COMPLEXITY:
 * ----------------------------------------------------
 * Worst case exponential because all combinations
 * may need to be generated.
 *
 * SPACE COMPLEXITY:
 * ----------------------------------------------------
 * O(N * 2^N)
 */

class WordBreakII {

    private Map<String, List<String>> memo = new HashMap<>();

    public List<String> wordBreak(String s, List<String> wordDict) {

        Set<String> dict = new HashSet<>(wordDict);

        return dfs(s, dict);
    }

    private List<String> dfs(String s, Set<String> dict) {

        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        List<String> result = new ArrayList<>();

        if (s.length() == 0) {
            result.add("");
            return result;
        }

        for (int i = 1; i <= s.length(); i++) {

            String prefix = s.substring(0, i);

            if (dict.contains(prefix)) {

                String remaining = s.substring(i);
                List<String> subSentences = dfs(remaining, dict);

                for (String sentence : subSentences) {

                    if (sentence.isEmpty()) {
                        result.add(prefix);
                    } else {
                        result.add(prefix + " " + sentence);
                    }
                }
            }
        }

        // Store in memo
        memo.put(s, result);

        return result;
    }

    public static void main(String[] args) {

        WordBreakII solution = new WordBreakII();

        String s = "catsanddog";

        List<String> wordDict = Arrays.asList(
                "cat",
                "cats",
                "and",
                "sand",
                "dog"
        );

        List<String> result = solution.wordBreak(s, wordDict);

        System.out.println("Possible Sentences:");

        for (String sentence : result) {
            System.out.println(sentence);
        }
    }
}