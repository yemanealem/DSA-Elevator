/**
 * LeetCode: Path Sum III
 *
 * Problem:
 * Given the root of a binary tree and an integer targetSum, 
 * return the number of paths where the sum of the values along the path equals targetSum.
 *
 * - The path does not need to start or end at the root or a leaf.
 * - The path must go downward (parent → child nodes).
 *
 * Approach:
 * 1. Use prefix sum + HashMap to track cumulative sums as long.
 * 2. For each node, compute current cumulative sum.
 * 3. If (currSum - targetSum) exists in map, it indicates a valid path ending at current node.
 * 4. Recurse left and right, backtracking after recursion.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

import java.util.HashMap;

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

public class PathSumIII {

    public int pathSum(TreeNode root, int targetSum) {
        HashMap<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1); // base case
        return dfs(root, 0L, targetSum, prefix);
    }

    private int dfs(TreeNode node, long currSum, int target, HashMap<Long, Integer> prefix) {
        if (node == null) return 0;

        currSum += node.val;

        int res = prefix.getOrDefault(currSum - target, 0);

        prefix.put(currSum, prefix.getOrDefault(currSum, 0) + 1);

        res += dfs(node.left, currSum, target, prefix);
        res += dfs(node.right, currSum, target, prefix);

        prefix.put(currSum, prefix.get(currSum) - 1); 

        return res;
    }
}
