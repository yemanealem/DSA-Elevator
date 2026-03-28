/*
LeetCode 144 - Binary Tree Preorder Traversal

QUESTION:
Given the root of a binary tree, return the preorder traversal of its nodes' values.
Preorder traversal follows:
    1. Root
    2. Left
    3. Right

HOW IT WORKS:
- Use DFS (recursion).
- Visit the root first, then traverse left, then right.

RUNNING TIME:
Time Complexity: O(n)
Space Complexity: O(h) where h is the height of the tree
*/

import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

class BinaryTreePreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    private void dfs(TreeNode node, List<Integer> result) {
        if (node == null) return;

        // Visit root
        result.add(node.val);

        // Traverse left
        dfs(node.left, result);

        // Traverse right
        dfs(node.right, result);
    }

    // Main method for testing
    public static void main(String[] args) {
        /*
            Construct the following tree:
                  1
                 / \
                2   3
               / \
              4   5

            Expected Preorder: [1, 2, 4, 5, 3]
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        BinaryTreePreorderTraversal sol = new BinaryTreePreorderTraversal();
        List<Integer> result = sol.preorderTraversal(root);

        System.out.println(result);
    }
}
