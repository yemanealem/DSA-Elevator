/*
==========================================
📌 PROBLEM: Edit Distance (Levenshtein Distance)

Given two strings word1 and word2, return the minimum number of operations
required to convert word1 into word2.

Allowed operations:
1. Insert a character
2. Delete a character
3. Replace a character

Example:
word1 = "horse"
word2 = "ros"
Output = 3

==========================================
🧠 HOW IT WORKS (DYNAMIC PROGRAMMING IDEA)

We define:

dp[i][j] = minimum number of operations needed
to convert word1[0..i-1] → word2[0..j-1]

Transition:

1. If characters match:
   dp[i][j] = dp[i-1][j-1]

2. If characters do NOT match:
   dp[i][j] = 1 + min(
       delete   → dp[i-1][j],
       insert   → dp[i][j-1],
       replace  → dp[i-1][j-1]
   )

Base cases:
- dp[i][0] = i (delete all characters)
- dp[0][j] = j (insert all characters)

==========================================
⏱ TIME & SPACE COMPLEXITY

Time Complexity:
O(m × n) → we fill an m by n DP table

Space Complexity:
O(m × n) → DP table storage

==========================================
*/

public class EditDistance {

    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // DP table
        int[][] dp = new int[m + 1][n + 1];

        // Base case: convert word1 → empty string (delete all)
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // Base case: convert empty string → word2 (insert all)
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                // If characters are the same, no operation needed
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } 
                else {
                    // Take minimum of insert, delete, replace
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j], 
                            Math.min(dp[i][j - 1], dp[i - 1][j - 1])
                    );
                }
            }
        }

        // Final answer
        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";

        System.out.println("Edit Distance: " + minDistance(word1, word2));
    }
}