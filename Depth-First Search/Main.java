/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) { 
        this.val = val; 
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {

    /*
    Problem:
    Return the sum of all left leaves in a binary tree.

    How it works:
    - Traverse the tree using DFS.
    - If a node is a leaf AND it is a left child → add its value.
    - Otherwise, recursively traverse left and right subtrees.

    Time Complexity: O(n)
    Space Complexity: O(h)
    */

    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root, false);
    }

    private int dfs(TreeNode node, boolean isLeft) {
        if (node == null) return 0;

        // Check if node is a leaf
        if (node.left == null && node.right == null) {
            return isLeft ? node.val : 0;
        }

        int leftSum = dfs(node.left, true);
        int rightSum = dfs(node.right, false);

        return leftSum + rightSum;
    }
}

public class Main {
    public static void main(String[] args) {

        /*
        Example Tree:
                3
               / \
              9   20
                  / \
                 15  7
        Left leaves: 9 and 15 → sum = 24
        */

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Solution solution = new Solution();
        int result = solution.sumOfLeftLeaves(root);

        System.out.println("Sum of Left Leaves: " + result);
    }
}
