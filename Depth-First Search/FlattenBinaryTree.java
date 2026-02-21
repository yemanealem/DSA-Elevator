import java.util.*;

/*
LeetCode Problem: Flatten Binary Tree to Linked List

Question:
Given the root of a binary tree, flatten the tree into a "linked list" in-place.

Rules:
- Use the same TreeNode structure.
- The linked list should follow Preorder traversal.
- Left pointer must be null.
- Right pointer should point to next node.

Preorder Traversal:
Root -> Left -> Right

---------------------------------------------------
Example Tree (Used in main):

        1
       / \
      2   5
     / \   \
    3   4   6

Preorder Order:
1 -> 2 -> 3 -> 4 -> 5 -> 6

Flattened Output:
1 -> 2 -> 3 -> 4 -> 5 -> 6

All left pointers become null.

---------------------------------------------------
How It Works (Reverse Preorder DFS):

We process nodes in this order:
Right -> Left -> Root

Why?
Because we rebuild the linked list from bottom to top.

Steps:
1. Recursively flatten right subtree.
2. Recursively flatten left subtree.
3. Set current.right = prev
4. Set current.left = null
5. Update prev = current

This connects nodes in preorder sequence.

---------------------------------------------------
Time Complexity:
O(n)
Each node is visited once.

Space Complexity:
O(h)
Where h is tree height (recursion stack).
Worst case O(n) for skewed tree.

---------------------------------------------------
Expected Output:
1 2 3 4 5 6
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class FlattenBinaryTree {

    private static TreeNode prev = null;

    public static void flatten(TreeNode root) {
        if (root == null) return;

        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }

    public static void main(String[] args) {

        /*
                1
               / \
              2   5
             / \   \
            3   4   6
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        flatten(root);

        // Print flattened list
        TreeNode current = root;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.right;
        }
    }
}
