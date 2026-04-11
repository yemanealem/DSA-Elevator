/**
 * Problem: Unique Binary Search Trees
 *
 * Given n, return the number of structurally unique BSTs
 * that store values from 1 to n.
 *
 * Approach:
 * We use Dynamic Programming.
 *
 * dp[n] = number of unique BSTs with n nodes
 *
 * Formula:
 * dp[n] = sum(dp[i-1] * dp[n-i]) for i from 1 to n
 *
 * Explanation:
 * - i is chosen as root
 * - left subtree has (i-1) nodes
 * - right subtree has (n-i) nodes
 * - total = left * right
 *
 * Base cases:
 * dp[0] = 1 (empty tree)
 * dp[1] = 1
 */

public class UniqueBST {

    public int numTrees(int n) {
        int[] dp = new int[n + 1];

        // Base cases
        dp[0] = 1;
        dp[1] = 1;

        for (int nodes = 2; nodes <= n; nodes++) {
            for (int root = 1; root <= nodes; root++) {
                dp[nodes] += dp[root - 1] * dp[nodes - root];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        UniqueBST solution = new UniqueBST();

        int n = 3; 
        int result = solution.numTrees(n);

        System.out.println("Number of unique BSTs for n = " + n + " is: " + result);
    }
}
