import java.util.*;

/*
Problem:
Given the root of a binary tree, return the postorder traversal of its nodes' values.

Postorder Traversal Order:
1. Visit Left Subtree
2. Visit Right Subtree
3. Visit Root Node

-----------------------------------
How It Works (Recursive Approach):
-----------------------------------
- We use a helper function to traverse the tree recursively.
- For each node:
    1. First recursively visit the left subtree.
    2. Then recursively visit the right subtree.
    3. Finally, add the current node value to the result list.

- The recursion ensures that:
    - Left children are fully processed first.
    - Then right children.
    - Then the parent node.

-----------------------------------
Example Tree:
        1
       / \
      2   3
     / \
    4   5

Traversal Steps:
- Start at root (1)
- Go left → (2)
- Go left → (4) → add 4
- Back to (2), go right → (5) → add 5
- Add 2
- Go to right subtree (3) → add 3
- Finally add root (1)

Result:
4 5 2 3 1

-----------------------------------
Time Complexity:
- O(n) → Each node is visited once.

Space Complexity:
- O(h) → Recursion stack (h = height of tree)
  Worst case: O(n), Best case (balanced tree): O(log n)
*/

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int x) {
        val = x;
    }
}

public class BinaryTreePostorderTraversal {

    public static void main(String[] args) {
        // Create sample binary tree
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

    // Main function that returns postorder traversal
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    // Recursive helper function
    private void postorder(TreeNode node, List<Integer> result) {
        // Base case: if node is null, stop recursion
        if (node == null) return;

        // Step 1: Traverse left subtree
        postorder(node.left, result);

        // Step 2: Traverse right subtree
        postorder(node.right, result);

        // Step 3: Visit (process) the root node
        result.add(node.val);
    }
}
