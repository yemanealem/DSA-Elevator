/*
========================================
📌 Question:
Balance a Binary Search Tree (LeetCode 1382)

💡 How it works (optimized):

- Perform inorder traversal → gives sorted order
- Instead of storing values in a list:
  👉 First, count total nodes
  👉 Then build BST using inorder simulation

- Use a global pointer (index)
- Build tree in O(N) time and O(N) space (recursion only)

========================================
⏱️ Running Time:
- Time Complexity: O(N)
- Space Complexity: O(H) → recursion only (better than O(N) list)

========================================
*/

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

class BalanceBSTOptimized {
    private int count = 0;
    private int index = 0;
    private int[] values;

    public TreeNode balanceBST(TreeNode root) {
        // Step 1: Count nodes
        countNodes(root);

        // Step 2: Store values in sorted order
        values = new int[count];
        inorder(root);

        // Step 3: Build balanced BST
        return buildBST(0, count - 1);
    }

    private void countNodes(TreeNode node) {
        if (node == null) return;
        count++;
        countNodes(node.left);
        countNodes(node.right);
    }

    private void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);
        values[index++] = node.val;
        inorder(node.right);
    }

    private TreeNode buildBST(int left, int right) {
        if (left > right) return null;

        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(values[mid]);

        node.left = buildBST(left, mid - 1);
        node.right = buildBST(mid + 1, right);

        return node;
    }
}

public class Main {
    public static void main(String[] args) {
        /*
                1
                 \
                  2
                   \
                    3
                     \
                      4
        */

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);

        BalanceBSTOptimized solution = new BalanceBSTOptimized();
        TreeNode balanced = solution.balanceBST(root);

        printInorder(balanced);
    }

    private static void printInorder(TreeNode node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }
}
