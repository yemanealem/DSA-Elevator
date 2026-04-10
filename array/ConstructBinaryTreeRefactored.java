import java.util.*;

/*
Refactored Version:
- No static globals
- Uses class-level variables (cleaner recursion)
- Same O(n) complexity, but better structure
*/

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class ConstructBinaryTreeRefactored {

    private int postIndex;
    private Map<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length - 1;
        inorderMap = new HashMap<>(inorder.length);

        // Build value → index map
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return build(inorder, postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] inorder, int[] postorder, int left, int right) {
        if (left > right) return null;

        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);

        int mid = inorderMap.get(rootVal);

        // Important: build right before left
        root.right = build(inorder, postorder, mid + 1, right);
        root.left  = build(inorder, postorder, left, mid - 1);

        return root;
    }

    // Helper for testing
    public static void printPreorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    public static void main(String[] args) {
        ConstructBinaryTreeRefactored obj = new ConstructBinaryTreeRefactored();

        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        TreeNode root = obj.buildTree(inorder, postorder);

        System.out.println("Preorder:");
        printPreorder(root);
    }
}
