import java.util.*;

/*
🧩 LeetCode 106: Construct Binary Tree from Inorder and Postorder Traversal

--------------------------------------------------
📌 PROBLEM:

Given two integer arrays:
1. inorder   (Left → Root → Right)
2. postorder (Left → Right → Root)

Construct and return the binary tree.

You may assume:
- All values are unique
- The tree can be reconstructed uniquely

--------------------------------------------------
📌 EXAMPLE:

Input:
inorder   = [9, 3, 15, 20, 7]
postorder = [9, 15, 7, 20, 3]

Output Tree:
        3
       / \
      9   20
         /  \
        15   7

--------------------------------------------------
🧠 HOW IT WORKS (DIVIDE & CONQUER):

Key Observations:

1️⃣ In POSTORDER:
   The LAST element is always the ROOT

   Example:
   postorder = [9, 15, 7, 20, 3]
                                ↑
                              ROOT

2️⃣ Find this ROOT in INORDER:
   inorder = [9, 3, 15, 20, 7]
                     ↑
                   ROOT

3️⃣ Split INORDER into:
   LEFT subtree  → [9]
   RIGHT subtree → [15, 20, 7]

4️⃣ Recursively build subtrees

--------------------------------------------------
⚠️ IMPORTANT TRICK:

We process POSTORDER from END → START

Postorder order:
Left → Right → Root

Reverse processing:
Root → Right → Left

👉 That’s why we must:
   build RIGHT subtree FIRST
   then LEFT subtree

--------------------------------------------------
⚙️ ALGORITHM STEPS:

1. Start from last index of postorder
2. Pick root value
3. Find its index in inorder using HashMap (O(1))
4. Recursively:
   - Build RIGHT subtree
   - Build LEFT subtree

--------------------------------------------------
⏱ TIME COMPLEXITY:
O(n) → each node processed once

⏱ SPACE COMPLEXITY:
O(n) → HashMap + recursion stack

--------------------------------------------------
*/

// Definition for binary tree node
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class ConstructBinaryTreeRefactored {

    // Pointer for postorder traversal
    private int postIndex;

    // Map to store value → index in inorder
    private Map<Integer, Integer> inorderMap;

    // Main function to build tree
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length - 1;
        inorderMap = new HashMap<>(inorder.length);

        // Build map for fast lookup
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return build(inorder, postorder, 0, inorder.length - 1);
    }

    // Recursive helper function
    private TreeNode build(int[] inorder, int[] postorder, int left, int right) {
        // Base case
        if (left > right) return null;

        // 1. Get root from postorder
        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);

        // 2. Find root index in inorder
        int mid = inorderMap.get(rootVal);

        // 3. Build RIGHT subtree first
        root.right = build(inorder, postorder, mid + 1, right);
