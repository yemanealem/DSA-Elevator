import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int x) {
        val = x;
    }
}

public class BinaryTreePostorderTraversal {

    // Postorder traversal: Left → Right → Root
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(TreeNode node, List<Integer> result) {
        if (node == null) return;

        postorder(node.left, result);   // Left
        postorder(node.right, result);  // Right
        result.add(node.val);           // Root
    }

    public static void main(String[] args) {
        // Build example tree:
        //         1
        //        / \
        //       2   3
        //      / \
        //     4   5

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        BinaryTreePostorderTraversal solution = new BinaryTreePostorderTraversal();
        List<Integer> result = solution.postorderTraversal(root);

        System.out.println("Postorder Traversal:");
        for (int val : result) {
            System.out.print(val + " ");
        }
    }
}
