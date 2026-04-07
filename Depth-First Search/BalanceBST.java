/*
========================================
📌 Question:
Balance a Binary Search Tree (LeetCode 1382)

Given the root of a Binary Search Tree (BST),
return a balanced BST with the same node values.

A balanced BST means:
- The depth of the two subtrees of every node never differs by more than 1.

========================================
💡 How it works:

- First, perform an inorder traversal of the BST.
  👉 This gives a sorted list of node values.

- Then, build a balanced BST from the sorted list:
  👉 Use the middle element as the root
  👉 Recursively build:
     - Left subtree from left half
     - Right subtree from right half

- Why this works:
  - Inorder traversal gives sorted data
  - Choosing the middle element ensures balance

========================================
⏱️ Running Time:

- Time Complexity: O(N)
  (inorder traversal + building BST)

- Space Complexity: O(N)
  (to store elements in a list)

========================================
*/

import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

class BalanceBST {
    
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> values = new ArrayList<>();

        // Step 1: Inorder traversal (sorted values)
        inorder(root, values);

        // Step 2: Build balanced BST
        return buildBST(values, 0, values.size() - 1);
    }

    private void inorder(TreeNode node, List<Integer> values) {
        if (node == null) return;

        inorder(node.left, values);
        values.add(node.val);
        inorder(node.right, values);
    }

    private TreeNode buildBST(List<Integer> values, int left, int right) {
        if (left > right) return null;

        int mid = left + (right - left) / 2;

        TreeNode node = new TreeNode(values.get(mid));

        node.left = buildBST(values, left, mid - 1);
        node.right = buildBST(values, mid + 1, right);

        return node;
    }
}

public class Main {
    public static void main(String[] args) {
        /*
                1
                 \
                  2
                   \
                    3
                     \
                      4
        (Unbalanced BST)
        */

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);

        BalanceBST solution = new BalanceBST();
        TreeNode balancedRoot = solution.balanceBST(root);

        // Print inorder of balanced BST
        printInorder(balancedRoot);
    }

    private static void printInorder(TreeNode node) {
        if (node == null) return;

        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }
}
