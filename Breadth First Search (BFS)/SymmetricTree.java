public class SymmetricTree {

    /*
     * LeetCode 101 - Symmetric Tree
     *
     * Idea:
     * A tree is symmetric if left subtree is mirror of right subtree.
     *
     * Mirror conditions:
     * 1. Both nodes null -> true
     * 2. One null -> false
     * 3. Values equal
     * 4. left.left == right.right
     * 5. left.right == right.left
     *
     * Time Complexity: O(n)
     * Space Complexity: O(h)  (recursion stack)
     */

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {

        if (left == null && right == null)
            return true;

        if (left == null || right == null)
            return false;

        if (left.val != right.val)
            return false;

        return isMirror(left.left, right.right) &&
               isMirror(left.right, right.left);
    }
}
