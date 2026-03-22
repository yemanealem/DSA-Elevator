/**
 * LeetCode: Count Complete Tree Nodes
 *
 * Given the root of a complete binary tree, return the number of nodes.
 *
 * A complete binary tree:
 * - All levels are fully filled except possibly the last
 * - Last level nodes are as far left as possible
 *
 * Approach:
 * - Compute leftmost height and rightmost height
 * - If equal → perfect tree → nodes = (2^h) - 1
 * - Else → recursively count left + right + root
 *
 * Time Complexity: O(log^2 n)
 * Space Complexity: O(log n)
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

public class CountCompleteTreeNodes {

    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = getLeftHeight(root);
        int rightHeight = getRightHeight(root);

        if (leftHeight == rightHeight) {
            return (1 << leftHeight) - 1;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private int getLeftHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }

    private int getRightHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.right;
        }
        return height;
    }
}
