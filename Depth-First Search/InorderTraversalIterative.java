import java.util.*;

/*
LeetCode Problem: Binary Tree Inorder Traversal

Question:
Given the root of a binary tree, return the inorder traversal 
of its nodes' values.

Inorder Traversal Rule (DFS):
Left -> Root -> Right

----------------------------------------
Example Tree Used in main():

        1
         \
          2
         /
        3

Expected Output:
[1, 3, 2]

----------------------------------------
How It Works (Iterative using Stack):

1. Use a stack to simulate recursion.
2. Move as left as possible and push nodes to stack.
3. When left becomes null:
      - Pop from stack
      - Add value to result
4. Move to right subtree.
5. Repeat until stack empty and current is null.

Trace:
Push 1
Left null -> pop 1 -> result = [1]
Move to 2
Push 2
Push 3
Left null -> pop 3 -> result = [1,3]
Pop 2 -> result = [1,3,2]

----------------------------------------
Time Complexity:
O(n) - each node visited once

Space Complexity:
O(n) worst case (skewed tree)

----------------------------------------
Final Output:
[1, 3, 2]
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class InorderTraversalIterative {

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {

            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            result.add(current.val);

            current = current.right;
        }

        return result;
    }

    public static void main(String[] args) {

        /*
                1
                 \
                  2
                 /
                3
        */

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        List<Integer> result = inorderTraversal(root);

        System.out.println(result);  // Output: [1, 3, 2]
    }
}
