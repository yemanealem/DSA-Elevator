/**
 * ------------------------------------------------------
 * 🧠 Problem: Binary Tree Maximum Path Sum
 * ------------------------------------------------------
 * A path is any sequence of nodes connected via parent-child links.
 *
 * Rules:
 * - Path can start and end anywhere
 * - Must contain at least one node
 * - Cannot reuse nodes
 *
 * Goal:
 * Find the maximum sum of any path in the tree.
 *
 * Example:
 *        -10
 *        /  \
 *       9   20
 *          /  \
 *         15   7
 *
 * Output: 42 (15 → 20 → 7)
 *
 * ------------------------------------------------------
 * 💡 How it works:
 * ------------------------------------------------------
 * At each node:
 * 1. Compute left gain
 * 2. Compute right gain
 * 3. Ignore negative gains (use 0)
 *
 * Two cases:
 *
 * A) Path THROUGH node (update answer):
 *    node.val + leftGain + rightGain
 *
 * B) Path going UP (return to parent):
 *    node.val + max(leftGain, rightGain)
 *
 * ------------------------------------------------------
 * ⏱️ Complexity:
 * ------------------------------------------------------
 * Time:  O(n)
 * Space: O(h)
 */

class BinaryTreeMaximumPathSum {

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int leftGain = Math.max(0, dfs(node.left));
        int rightGain = Math.max(0, dfs(node.right));

        int currentPathSum = node.val + leftGain + rightGain;

        maxSum = Math.max(maxSum, currentPathSum);

        return node.val + Math.max(leftGain, rightGain);
    }
}
