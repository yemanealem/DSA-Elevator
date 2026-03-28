/*
LeetCode 111 - Minimum Depth of Binary Tree

QUESTION:
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root
node down to the nearest leaf node.

HOW IT WORKS:
- Use Breadth-First Search (BFS) level order traversal.
- Traverse the tree level by level.
- As soon as we encounter the first leaf node (a node with no children),
  we return the current depth.
- This works because BFS guarantees that we visit nodes in increasing depth order,
  so the first leaf we find is the minimum depth.

RUNNING TIME:
Time Complexity: O(n) — we may visit each node once in the worst case.
Space Complexity: O(n) — queue may hold up to all nodes at one level.
*/

import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

class MinimumDepthOfBinaryTree {

    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // Check if it's a leaf node
                if (node.left == null && node.right == null) {
                    return depth;
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            depth++;
        }

        return depth;
    }
}
