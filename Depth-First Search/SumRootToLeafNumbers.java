
/*
LeetCode Problem: Sum Root to Leaf Numbers

Question:
You are given the root of a binary tree containing digits from 0–9 only.
Each root-to-leaf path represents a number.

Return the total sum of all root-to-leaf numbers.

A leaf is a node with no children.

----------------------------------------------------
Example Tree (Used in main):

        1
       / \
      2   3

Root-to-leaf paths:
1 -> 2  = 12
1 -> 3  = 13

Total Sum:
12 + 13 = 25

----------------------------------------------------
How It Works (DFS Approach):

Idea:
We build the number while traversing from root to leaf.

At each node:
currentNumber = previousNumber * 10 + node.val

When we reach a leaf:
Return the constructed number.

Steps:
1. Start DFS from root with currentSum = 0.
2. Multiply currentSum by 10 and add node value.
3. If node is leaf → return currentSum.
4. Otherwise:
   return leftSum + rightSum.

----------------------------------------------------
Example Trace:

Start at 1:
current = 0*10 + 1 = 1

Go left to 2:
current = 1*10 + 2 = 12 (leaf → return 12)

Go right to 3:
current = 1*10 + 3 = 13 (leaf → return 13)

Return 12 + 13 = 25

----------------------------------------------------
Time Complexity:
O(n)
Each node visited once.

Space Complexity:
O(h)
Recursion stack (h = tree height).
Worst case O(n) for skewed tree.

----------------------------------------------------
Expected Output:
25
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class SumRootToLeafNumbers {

    public static int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private static int dfs(TreeNode node, int currentSum) {
        if (node == null) return 0;

        currentSum = currentSum * 10 + node.val;

        // If leaf node
        if (node.left == null && node.right == null) {
            return currentSum;
        }

        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }

    public static void main(String[] args) {

        /*
                1
               / \
              2   3
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        int result = sumNumbers(root);

        System.out.println(result);  // Output: 25
    }
}
