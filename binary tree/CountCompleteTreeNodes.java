/**
 * LeetCode: Count Complete Tree Nodes
 *
 * Problem:
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 *
 * A complete binary tree is defined as:
 * - Every level is completely filled except possibly the last
 * - All nodes in the last level are as far left as possible
 *
 * -------------------------------------------------------
 * Optimized Approach (Using Tree Height):
 *
 * Instead of traversing all nodes (O(n)),
 * we take advantage of the properties of a complete tree.
 *
 * Steps:
 * 1. Compute the height of the leftmost path → leftHeight
 * 2. Compute the height of the rightmost path → rightHeight
 *
 * - If leftHeight == rightHeight:
 *     → Tree is a perfect binary tree
 *     → Number of nodes = (2^height) - 1
 *
 * - Else:
 *     → Recursively count nodes in left and right subtrees
 *
 * -------------------------------------------------------
 * Example:
 *
 *        1
 *      /   \
 *     2     3
 *    / \   /
 *   4   5 6
 *
 * leftHeight = 3, rightHeight = 2 → not perfect
 * So recurse on left and right
 *
 * -------------------------------------------------------
 * Time Complexity:
 * O(log² n)
 * - Each height calculation takes O(log n)
 * - Done for each recursive call
 *
 * Space Complexity:
 * O(log n) → recursion stack
 *
 * -------------------------------------------------------
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

        // If perfect binary tree
        if (leftHeight == rightHeight) {
            return (1 << leftHeight) - 1; // 2^h - 1
        }

        // Otherwise recurse
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

    // Test traversal (optional)
    public static void main(String[] args) {
        CountCompleteTreeNodes obj = new CountCompleteTreeNodes();

        // Manually build tree:
        //        1
        //      /   \
        //     2     3
        //    / \   /
        //   4   5 6

        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)),
                new TreeNode(3,
                        new TreeNode(6),
                        null));

        int count = obj.countNodes(root);

        System.out.println("Total Nodes: " + count);
    }
}
