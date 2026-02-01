import java.util.*;

class Solution {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        backtrack(root, new StringBuilder(), res);
        return res;
    }

    private void backtrack(TreeNode node, StringBuilder path, List<String> res) {
        if (node == null) return;

        int len = path.length();   // save state (BACKTRACKING key)

        path.append(node.val);

        // if leaf node → record path
        if (node.left == null && node.right == null) {
            res.add(path.toString());
        } else {
            path.append("->");
            backtrack(node.left, path, res);
            backtrack(node.right, path, res);
        }

        path.setLength(len); // undo change (BACKTRACK)
    }
}
