/*
Question:
Given a binary tree, find the Lowest Common Ancestor (LCA)
of two given nodes p and q.

The Lowest Common Ancestor is the lowest node in the tree
that has both p and q as descendants
(a node can be a descendant of itself).

--------------------------------------------------

How It Works (Depth First Search):

1. Start DFS traversal from the root.

2. Base Case:
   - If root is null -> return null
   - If root equals p or q -> return root

3. Recursively search:
   - left subtree
   - right subtree

4. Decision:
   - If both left and right return non-null,
     current node is the Lowest Common Ancestor

   - If only one side is non-null,
     return that side upward

--------------------------------------------------

Example:

        3
       / \
      5   1
     / \ / \
    6  2 0  8

LCA of 5 and 1 = 3

--------------------------------------------------

Running Time:
O(n)

Reason:
Each node is visited once.

--------------------------------------------------

Space Complexity:
O(h)

h = height of tree

Reason:
Recursive DFS call stack.
Worst case:
O(n) for skewed tree
*/

class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class LowestCommonAncestorOfBinaryTree {

    // Depth First Search
    public static TreeNode lowestCommonAncestor(
            TreeNode root,
            TreeNode p,
            TreeNode q) {

        // Base case
        if (root == null || root == p || root == q) {
            return root;
        }

        // Search left subtree
        TreeNode left =
                lowestCommonAncestor(root.left, p, q);

        // Search right subtree
        TreeNode right =
                lowestCommonAncestor(root.right, p, q);

        // If both sides return non-null,
        // current node is LCA
        if (left != null && right != null) {
            return root;
        }

        // Return non-null side
        return left != null ? left : right;
    }

    public static void main(String[] args) {

        /*
                 3
                / \
               5   1
              / \ / \
             6  2 0  8
        */

        TreeNode root = new TreeNode(3);

        root.left = new TreeNode(5);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        TreeNode p = root.left;   // Node 5
        TreeNode q = root.right;  // Node 1

        TreeNode lca =
                lowestCommonAncestor(root, p, q);

        System.out.println(
                "Lowest Common Ancestor: " + lca.val);
    }
}