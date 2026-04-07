class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

class RangeSumBST {
    public int rangeSumBST(TreeNode root, int low, int high) {
        return dfs(root, low, high);
    }

    private int dfs(TreeNode node, int low, int high) {
        if (node == null) return 0;

        if (node.val < low) {
            return dfs(node.right, low, high);
        }

        if (node.val > high) {
            return dfs(node.left, low, high);
        }

        return node.val 
                + dfs(node.left, low, high) 
                + dfs(node.right, low, high);
    }
}

public class Main {
    public static void main(String[] args) {
        /*
              10
             /  \
            5    15
           / \     \
          3   7     18
        */

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);

        int low = 7;
        int high = 15;

        RangeSumBST rangeSumBST = new RangeSumBST();
        int result = rangeSumBST.rangeSumBST(root, low, high);

        System.out.println("Range Sum: " + result); // Output: 32
    }
}
