public class EditDistance {

    /*
     * PROBLEM (Edit Distance / Levenshtein Distance):
     * ------------------------------------------------
     * Given two strings word1 and word2, return the minimum number
     * of operations required to convert word1 into word2.
     *
     * Allowed operations:
     * 1. Insert a character
     * 2. Delete a character
     * 3. Replace a character
     *
     * Example:
     * Input:  word1 = "horse", word2 = "ros"
     * Output: 3
     *
     * ------------------------------------------------
     * HOW IT WORKS (Dynamic Programming):
     * ------------------------------------------------
     * We use a 2D DP table where:
     *
     * dp[i][j] = minimum operations to convert
     *            first i characters of word1
     *            to first j characters of word2
     *
     * Step 1: Base Cases
     * - dp[0][j] = j → insert all characters of word2
     * - dp[i][0] = i → delete all characters of word1
     *
     * Step 2: Transition
     * - If characters match:
     *      dp[i][j] = dp[i-1][j-1]
     *
     * - If characters don’t match:
     *      dp[i][j] = 1 + min(
     *          dp[i-1][j],   // delete
     *          dp[i][j-1],   // insert
     *          dp[i-1][j-1]  // replace
     *      )
     *
     * Why +1?
     * Because we perform one operation.
     *
     * ------------------------------------------------
     * TIME & SPACE COMPLEXITY:
     * ------------------------------------------------
     * Time Complexity:  O(m × n)
     * Space Complexity: O(m × n)
     *
     * Where:
     * m = length of word1
     * n = length of word2
     */

    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        // Base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // delete all characters
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // insert all characters
        }

        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // no operation
                } else {
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j], // delete
                        Math.min(
                            dp[i][j - 1], // insert
                            dp[i - 1][j - 1] // replace
                        )
                    );
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";

        int result = minDistance(word1, word2);

        System.out.println("Edit Distance: " + result);
    }
}