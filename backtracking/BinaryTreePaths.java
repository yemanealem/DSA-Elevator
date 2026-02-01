import java.util.*;

/*
================================================================================
LeetCode 257 – Binary Tree Paths

QUESTION:
Given the root of a binary tree, return all root-to-leaf paths.
A path is defined as a sequence of nodes from the root node to a leaf node.
Each path should be represented as a string with "->" separating the values.

Example Tree:
        1
       / \
      2   3
       \
        5

Output:
["1->2->5", "1->3"]


APPROACH (BACKTRACKING + DFS):
We traverse the tree using Depth-First Search (DFS).
We maintain a "path" that stores the current root-to-node path.

At each node:
1) ADD the current node value to the path (choose)
2) If the node is a LEAF, save the path
3) Otherwise, continue DFS to left and right children
4) REMOVE the added value before returning (backtrack)

Backtracking ensures that each recursive call works with the correct path.


HOW IT WORKS – STEP-BY-STEP TRACE:

Start at root:
path = ""

Visit node 1:
path = "1"
(not a leaf)

Go LEFT to node 2:
path = "1->2"
(not a leaf)

Go RIGHT to node 5:
path = "1->2->5"
(node 5 is a leaf)
→ SAVE "1->2->5"

Backtrack:
path restored to "1->2"
return to node 1

Go RIGHT to node 3:
path = "1->3"
(node 3 is a leaf)
→ SAVE "1->3"

Final Result:
["1->2->5", "1->3"]


WHY BACKTRACKING IS IMPORTANT:
- We reuse the same path object
- We avoid creating unnecessary strings
- We restore state after each recursive call
- This guarantees correctness and efficiency


TIME & SPACE COMPLEXITY:
Time:  O(N)  → each node is visited once
Space: O(H)  → recursion stack (tree height)

================================================================================
*/

public class BinaryTreePaths {

    // Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // Solution using backtracking
    static class Solution {

        public List<String> binaryTreePaths(TreeNode root) {
            List<String> result = new ArrayList<>();
            backtrack(root, new StringBuilder(), result);
            return result;
        }

        private void backtrack(TreeNode node, StringBuilder path, List<String> result) {
            if (node == null) return;

            int lengthBefore = path.length(); // save state

            path.append(node.val);

            // if leaf node, record path
            if (node.left == null && node.right == null) {
                result.add(path.toString());
            } else {
                path.append("->");
                backtrack(node.left, path, result);
                backtrack(node.right, path, result);
            }

            path.setLength(lengthBefore); // BACKTRACK
        }
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {

        /*
            Constructing the tree:
                    1
                   / \
                  2   3
                   \
                    5
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        Solution solution = new Solution();
        List<String> paths = solution.binaryTreePaths(root);

        System.out.println("Binary Tree Paths:");
        for (String path : paths) {
            System.out.println(path);
        }
    }
}
