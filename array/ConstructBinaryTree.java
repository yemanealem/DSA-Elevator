import java.util.*;

/*
🧩 LeetCode 106: Construct Binary Tree from Inorder and Postorder Traversal

PROBLEM:
Given two integer arrays inorder and postorder where:
- inorder = Left → Root → Right
- postorder = Left → Right → Root

Construct and return the binary tree.

--------------------------------------------------

HOW IT WORKS (DIVIDE & CONQUER):

1️⃣ The last element of postorder is always the ROOT

2️⃣ Find the root in inorder array
   → Elements on left = left subtree
   → Elements on right = right subtree

3️⃣ Recursively build:
   - Right subtree FIRST (important!)
   - Then left subtree

Why right first?
Because in postorder, elements are:
Left → Right → Root
So when traversing backwards, we get:
Root → Right → Left

--------------------------------------------------

TIME COMPLEXITY:
O(n) using HashMap

SPACE COMPLEXITY:
O(n)

--------------------------------------------------
*/

// Tree Node definition
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class ConstructBinaryTree {

    static int postIndex;
    static Map<Integer, Integer> inorderMap;

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length - 1;
        inorderMap = new HashMap<>();

        // Store inorder index for quick lookup
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return helper(inorder, postorder, 0, inorder.length - 1);
    }

    private static TreeNode helper(int[] inorder, int[] postorder, int left, int right) {
        if (left > right) return null;

        // Get root from postorder
        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);

        // Find root in inorder
        int index = inorderMap.get(rootVal);

        // Build RIGHT subtree first
        root.right = helper(inorder, postorder, index + 1, right);

        // Build LEFT subtree
        root.left = helper(inorder, postorder, left, index - 1);

        return root;
    }

    // Preorder print to verify tree
    public static void printPreorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        TreeNode root = buildTree(inorder, postorder);

        System.out.println("Preorder traversal of constructed tree:");
        printPreorder(root);
    }
}
