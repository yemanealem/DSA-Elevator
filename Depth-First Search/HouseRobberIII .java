class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class HouseRobberIII {

    /**
     * House Robber III using DFS + Dynamic Programming
     *
     * result[0] = max money if current node is skipped
     * result[1] = max money if current node is robbed
     *
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     */

    public int rob(TreeNode root) {

        int[] result = dfs(root);

        return Math.max(result[0], result[1]);
    }

    private int[] dfs(TreeNode node) {

        if (node == null) {
            return new int[]{0, 0};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int rob = node.val + left[0] + right[0];
        int skip = Math.max(left[0], left[1])
                 + Math.max(right[0], right[1]);

        return new int[]{skip, rob};
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);

        HouseRobberIII solution = new HouseRobberIII();

        int result = solution.rob(root);

        System.out.println("Maximum money robbed: " + result);
    }
}