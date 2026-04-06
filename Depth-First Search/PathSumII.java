import java.util.*;

/*
Problem: Path Sum II (LeetCode)

Given a binary tree and a target sum, return all root-to-leaf paths
where each path's sum equals the given target.

Approach:
- Use DFS (Depth-First Search) + Backtracking
- Track current path and remaining sum
- When reaching a leaf and sum == 0 → add path to result

Time Complexity: O(N)
Space Complexity: O(H) recursion stack
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

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void dfs(TreeNode node, int remainingSum,
                     List<Integer> path,
                     List<List<Integer>> result) {

        if (node == null) return;

        path.add(node.val);
        remainingSum -= node.val;

        // check leaf
        if (node.left == null && node.right == null && remainingSum == 0) {
            result.add(new ArrayList<>(path));
        }

        dfs(node.left, remainingSum, path, result);
        dfs(node.right, remainingSum, path, result);

        // backtracking
        path.remove(path.size() - 1);
    }
}

public class Main {

    public static void main(String[] args) {

        /*
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \     / \
        7    2   5   1
        */

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);

        root.left
