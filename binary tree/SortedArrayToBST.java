// Definition for a binary tree node
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

// Main class (also contains solution)
public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right) {
        if (left > right) return null;

        int mid = left + (right - left) / 2;

        TreeNode root = new TreeNode(nums[mid]);

        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);

        return root;
    }

    // Preorder traversal for testing
    public void preorder(TreeNode node) {
        if (node == null) return;

        System.out.print(node.val + " ");
        preorder(node.left);
        preorder(node.right);
    }

    // Main method
    public static void main(String[] args) {
        SortedArrayToBST obj = new SortedArrayToBST();

        int[] nums = {-10, -3, 0, 5, 9};

        TreeNode root = obj.sortedArrayToBST(nums);

        System.out.print("Preorder Traversal: ");
        obj.preorder(root);
    }
}
