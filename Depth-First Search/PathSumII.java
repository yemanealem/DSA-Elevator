/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

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

        // add current node to path
        path.add(node.val);

        // subtract value from remaining sum
        remainingSum -= node.val;

        // check if it's a leaf and sum is matched
        if (node.left == null && node.right == null && remainingSum == 0) {
            result.add(new ArrayList<>(path)); // copy path
        }

        // go deeper
        dfs(node.left, remainingSum, path, result);
        dfs(node.right, remainingSum, path, result);

        // backtrack (VERY IMPORTANT)
        path.remove(path.size() - 1);
    }
}
