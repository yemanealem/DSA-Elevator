/*
LeetCode 144 - Binary Tree Preorder Traversal

QUESTION:
Given the root of a binary tree, return the preorder traversal of its nodes' values.
Preorder traversal follows the order:
    1. Root
    2. Left subtree
    3. Right subtree

HOW IT WORKS:
- Use Depth First Search (DFS).
- Visit the current node first (root).
- Then recursively visit the left subtree.
- Finally, recursively visit the right subtree.
- Add each visited node's value to the result list in this order.

RUNNING TIME:
Time Complexity: O(n) — each node is visited exactly once.
Space Complexity: O(h) — recursion stack, where h is the height of the tree.
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

        // 1. Visit root
        result.add(node.val);

        // 2. Traverse left
        dfs(node.left, result);

        // 3. Traverse right
        dfs(node.right, result);
    }
}


