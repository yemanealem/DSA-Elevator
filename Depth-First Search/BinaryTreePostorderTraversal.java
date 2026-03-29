import java.util.*;

/*
Problem:
Given the root of a binary tree, return the postorder traversal of its nodes' values.

Postorder Traversal:
- Visit Left subtree
- Visit Right subtree
- Visit Root

Example:
        1
       / \
      2   3
     / \
    4   5

Output:
4 5 2 3 1

Time Complexity: O(n)
- Each node is visited exactly once.

Space Complexity:
- O(h) where h is the height of the tree (due to recursion stack)
*/

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int x) {
        val = x;
    }
}

public class BinaryTreePostorderTraversal {

    // Main method to run the program
    public static void main(String[] args) {
        // Build example tree:
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

    // Function to perform postorder traversal
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    // Helper recursive function
    private void postorder(TreeNode node, List<Integer> result) {
        if (node == null) return;

        postorder(node.left, result);   // Left
        postorder(node.right, result);  // Right
        result.add(node.val);           // Root
    }
}
