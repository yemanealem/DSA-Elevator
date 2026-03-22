/**
 * LeetCode: Convert Sorted Array to Binary Search Tree
 *
 * Problem:
 * Given an integer array nums where the elements are sorted in ascending order,
 * convert it to a height-balanced binary search tree (BST).
 *
 * A height-balanced BST is defined as a binary tree in which the depth of the
 * two subtrees of every node never differs by more than 1.
 *
 * -------------------------------------------------------
 * How it works (Approach - Divide and Conquer):
 *
 * 1. Since the array is sorted, the middle element becomes the root.
 * 2. Left half of the array → left subtree
 * 3. Right half of the array → right subtree
 * 4. Repeat this process recursively
 *
 * This ensures the tree remains balanced.
 *
 * -------------------------------------------------------
 * Example Trace:
 *
 * Input: [-10, -3, 0, 5, 9]
 *
 * Step 1: mid = 2 → root = 0
 * Step 2: left = [-10, -3]
 *         mid = 0 → node = -10
 *         right child = -3
 *
 * Step 3: right = [5, 9]
 *         mid = 3 → node = 5
 *         right child = 9
 *
 * Final Tree (one possible):
 *        0
 *       / \
 *    -10   5
 *      \     \
 *      -3     9
 *
 * -------------------------------------------------------
 * Time Complexity:
 * O(n) → Each element is processed exactly once
 *
 * Space Complexity:
 * O(log n) → Recursive stack (height of balanced tree)
 *
 * -------------------------------------------------------
 */

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

    public static void main(String[] args) {
        SortedArrayToBST obj = new SortedArrayToBST();

        int[] nums = {-10, -3, 0, 5, 9};

        TreeNode root = obj.sortedArrayToBST(nums);

        System.out.print("Preorder Traversal: ");
        obj.preorder(root);
    }
}
