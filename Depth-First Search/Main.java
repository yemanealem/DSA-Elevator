/**
 * ---------------------------------------------------------
 * Problem: Sum of Left Leaves
 * ---------------------------------------------------------
 * Given the root of a binary tree, return the sum of all left leaves.
 *
 * A "left leaf" is a node that:
 * - is the left child of its parent
 * - AND is a leaf node (has no children)
 *
 * Example:
 *         3
 *        / \
 *       9   20
 *           / \
 *          15  7
 *
 * Left leaves: 9, 15
 * Output: 24
 *
 * ---------------------------------------------------------
 * How It Works:
 * ---------------------------------------------------------
 * - We use Depth-First Search (DFS) to traverse the tree.
 * - For each node:
 *     1. If the node is null → return 0.
 *     2. If the node is a leaf:
 *         - Return its value ONLY if it is a left child.
 *     3. Otherwise:
 *         - Recursively calculate:
 *             - Left subtree (marking child as left = true)
 *             - Right subtree (marking child as left = false)
 * - Finally, sum up all valid left leaf values.
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * - Each node is visited once.
 *
 * Space Complexity: O(h)
 * - h = height of the tree (recursion stack)
 * ---------------------------------------------------------
 */

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) { 
        this.val = val; 
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {

    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root, false);
    }

    private int dfs(TreeNode node, boolean isLeft) {
        if (node == null) return 0;

        // If it's a leaf node
        if (node.left == null && node.right == null) {
            return isLeft ? node.val : 0;
        }

        // Recursively check left and right subtrees
        int leftSum = dfs(node.left, true);
        int rightSum = dfs(node.right, false);

        return leftSum + rightSum;
    }
}

public class Main {
    public static void main(String[] args) {

        // Construct the example tree:
        //         3
        //        / \
        //       9   20
        //           / \
        //          15  7

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Solution solution = new Solution();
        int result = solution.sumOfLeftLeaves(root);

        System.out.println("Sum of Left Leaves: " + result);
    }
}
