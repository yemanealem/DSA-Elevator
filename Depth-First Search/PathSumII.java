import java.util.*;

/*
Problem: Path Sum II (LeetCode)

Question:
Given the root of a binary tree and an integer targetSum, return all root-to-leaf
paths where the sum of the node values in the path equals targetSum.
A leaf is a node with no children.

How it works:
- We use Depth-First Search (DFS) with Backtracking.
- Start from the root and explore all paths down to the leaves.
- Maintain:
    1. A list (path) to store the current path.
    2. A remainingSum (targetSum - node values along the path).
- At each node:
    - Add the node value to the path.
    - Subtract it from remainingSum.
- If we reach a leaf node AND remainingSum == 0:
    → We found a valid path, so we add a copy of it to the result.
- Then we backtrack:
    → Remove the last element from the path to explore other branches.

Why backtracking?
- Because we reuse the same path list while exploring different branches.

Time Complexity:
- O(N), where N is the number of nodes.
  We visit each node once.

Space Complexity:
- O(H), where H is the height of the tree (recursion stack).
- Additional space for storing paths in the result.
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

class PathSumII {

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

        // if leaf node and sum matches
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

        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);

        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        PathSumII solver = new PathSumII();
        List<List<Integer>> result = solver.pathSum(root, 22);

        System.out.println("Paths with sum 22:");
        for (List<Integer> path : result) {
            System.out.println(path);
        }
    }
}
