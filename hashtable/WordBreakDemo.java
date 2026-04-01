import java.util.*;

/*
================================= WORD BREAK PROBLEM =================================

Problem:
Given a string s and a dictionary of strings wordDict,
return true if s can be segmented into a space-separated
sequence of one or more dictionary words.

Example:
Input:  s = "leetcode"
        wordDict = ["leet", "code"]
Output: true
Explanation: "leetcode" = "leet" + "code"

--------------------------------------------------------------------------------------

How It Works (Optimized DP + HashSet):

1. Convert wordDict into a HashSet
   -> This gives O(1) lookup time.

2. Use Dynamic Programming:
   dp[i] = true  → substring s[0:i] can be formed using dictionary words

3. Initialization:
   dp[0] = true → empty string is always valid

4. For each position i:
   - Check previous positions j (from i backwards)
   - Only check substrings of length <= max word length (optimization)
   - If:
        dp[j] == true
        AND substring s[j:i] exists in dictionary
     → then mark dp[i] = true

5. Final Answer:
   dp[s.length()]

--------------------------------------------------------------------------------------

Key Optimizations:
- Limit inner loop using max word length → reduces unnecessary checks
- Break early when match is found
- Use HashSet instead of List for faster lookup

Time Complexity:
O(n * maxWordLength)

Space Complexity:
O(n)

======================================================================================
*/

public class WordBreakDemo {

    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);

        // Find max word length (optimization)
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0 && (i - j) <= maxLen; j--) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");

        boolean result = wordBreak(s, wordDict);

        System.out.println("Can the string be segmented? " + result);
    }
}
