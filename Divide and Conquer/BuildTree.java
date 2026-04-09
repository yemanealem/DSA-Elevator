import java.util.HashMap;
import java.util.Map;

/*
Problem: Construct Binary Tree from Preorder and Inorder Traversal

Given:
- preorder (Root -> Left -> Right)
- inorder  (Left -> Root -> Right)

Goal:
Reconstruct the binary tree.

------------------------------------------------------------
How it works using (Divide & Conquer):

1. The first element in preorder is always the root.

2. Find this root in inorder array:
   - Left side = left subtree
   - Right side = right subtree

3. Recursively build:
   - Left subtree
   - Right subtree

4. Combine them into the root.

------------------------------------------------------------
Time Complexity:
   O(n)
   (Using HashMap to find index in O(1))

Space Complexity:
   O(n)
   (Recursion + map)

------------------------------------------------------------
*/

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BuildTree {

    private Map<Integer, Integer> map = new HashMap<>();
    private int preIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        // Store inorder indices for fast lookup
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return helper(preorder, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] preorder, int inStart, int inEnd) {

        // Base case
        if (inStart > inEnd) return null;

        // Root from preorder
        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);

        // Find root index in inorder
        int index = map.get(rootVal);

        // Build left and right subtree
        root.left = helper(preorder, inStart, index - 1);
        root.right = helper(preorder, index + 1, inEnd);

        return root;
    }

    public static void main(String[] args) {
        BuildTree bt = new BuildTree();

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder  = {9, 3, 15, 20, 7};

        TreeNode root = bt.buildTree(preorder, inorder);

        System.out.println("Tree constructed successfully!");

        /*
        
        preorder = [3, 9, 20, 15, 7]
inorder  = [9, 3, 15, 20, 7]

Tree:
        3
       / \
      9  20
         / \
        15  7
        
        */
    }
}
