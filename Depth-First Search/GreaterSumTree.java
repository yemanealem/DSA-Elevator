/*
========================================
📌 Question:
Binary Search Tree to Greater Sum Tree (LeetCode 1038)

Given the root of a Binary Search Tree (BST),
convert it into a Greater Sum Tree (GST) where:

- Each node's value is replaced by the sum of all values
  greater than or equal to that node's value in the BST.

========================================
💡 How it works:

- In a BST:
  Left < Root < Right

- To compute "greater sum", we must visit nodes in:
  👉 Reverse Inorder Traversal (Right → Root → Left)

- Why?
  - Right subtree contains larger values
  - We process largest values first and keep a running sum

- Steps:
  1. Traverse the right subtree first
  2. Maintain a running sum
  3. Update current node value with running sum
  4. Add current node value to running sum
  5. Traverse the left subtree

- This ensures each node gets the sum of all greater or equal values.

========================================
⏱️ Running Time:

- Time Complexity: O(N)
  (each node is visited exactly once)

- Space Complexity: O(H)
  (recursion stack, where H = height of the tree)

========================================
*/

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

class GreaterSumTree {
    private int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        reverseInorder(root);
        return root;
    }

    private void reverseInorder(TreeNode node) {
        if (node == null) return;

        // Traverse right subtree first (larger values)
        reverseInorder(node.right);

        // Update current node
        sum += node.val;
        node.val = sum;

        // Traverse left subtree
        reverseInorder(node.left);
    }
}

public class Main {
    public static void main(String[] args) {
        /*
                4
               / \
              1   6
             / \  / \
            0  2  5  7
                 \     \
                  3     8
        */

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(8);

        GreaterSumTree gst = new GreaterSumTree();
        gst.bstToGst(root);

        // Print result using inorder (just for verification)
        printInorder(root);
    }

    private static void printInorder(TreeNode node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }
}
