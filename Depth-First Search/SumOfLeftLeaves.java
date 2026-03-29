
/*
Problem:
Given the root of a binary tree, return the sum of all left leaves.

A "left leaf" is a node that:
- is the left child of its parent
- AND has no children (i.e., it is a leaf node)

-----------------------------------
How It Works (Recursive Approach):
-----------------------------------
- We traverse the tree using recursion.
- At each node:
    1. Check if the left child exists and is a leaf:
        - If yes, add its value to the sum.
    2. Recursively process the left subtree.
    3. Recursively process the right subtree.

- Important:
  We only add values of nodes that are:
  - Left children
  - AND have no left or right children

-----------------------------------
Example Tree:
        3
       / \
      9   20
          / \
         15  7

Left leaves:
- 9 (left of 3, and a leaf)
- 15 (left of 20, and a leaf)

Result:
9 + 15 = 24

-----------------------------------
Time Complexity:
- O(n) → Each node is visited once

Space Complexity:
- O(h) → Recursion stack (h = height of tree)
*/

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int x) {
        val = x;
    }
}

public class SumOfLeftLeaves {

    public static void main(String[] args) {
        // Build example tree:
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        SumOfLeftLeaves solution = new SumOfLeftLeaves();

        int result = solution.sumOfLeftLeaves(root);

        System.out.println("Sum of Left Leaves: " + result);
    }

    // Main function
    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root, false);
    }

    /*
    DFS helper function

    Parameters:
    - node: current node
    - isLeft: indicates whether this node is a left child

    Logic:
    - If node is null → return 0
    - If node is a leaf AND isLeft → return its value
    - Otherwise → recurse left + recurse right
    */
    private int dfs(TreeNode node, boolean isLeft) {
        if (node == null) return 0;

        // Check if this node is a leaf
        if (node.left == null && node.right == null) {
            return isLeft ? node.val : 0;
        }

        // Recursively sum left and right subtrees
        int leftSum = dfs(node.left, true);
        int rightSum = dfs(node.right, false);

        return leftSum + rightSum;
    }
}
