/*
========================================
📌 Question:
Range Sum of BST (LeetCode 938)

Given the root of a Binary Search Tree (BST) and two integers low and high,
return the sum of values of all nodes with a value in the range [low, high].

========================================
💡 How it works:

- A Binary Search Tree (BST) has the property:
  Left subtree < Root < Right subtree

- We use this property to optimize traversal:
  1. If current node value < low:
     → Skip left subtree (all values are smaller)
     → Only explore right subtree

  2. If current node value > high:
     → Skip right subtree (all values are larger)
     → Only explore left subtree

  3. If current node value is within [low, high]:
     → Add its value
     → Explore both left and right subtrees

- This reduces unnecessary traversal and improves efficiency.

========================================
⏱️ Running Time:

- Worst Case: O(N)
  (if all nodes are within the range or tree is skewed)

- Best/Average Case: Better than O(N)
  (due to pruning using BST properties)

- Space Complexity: O(H)
  where H is the height of the tree (recursion stack)

========================================
*/

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

class RangeSumBST {
    public int rangeSumBST(TreeNode root, int low, int high) {
        return dfs(root, low, high);
    }

    private int dfs(TreeNode node, int low, int high) {
        if (node == null) return 0;

        if (node.val < low) {
            return dfs(node.right, low, high);
        }

        if (node.val > high) {
            return dfs(node.left, low, high);
        }

        return node.val 
                + dfs(node.left, low, high) 
                + dfs(node.right, low, high);
    }
}

public class Main {
    public static void main(String[] args) {
        /*
              10
             /  \
            5    15
           / \     \
          3   7     18
        */

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);

        int low = 7;
        int high = 15;

        RangeSumBST rangeSumBST = new RangeSumBST();
        int result = rangeSumBST.rangeSumBST(root, low, high);

        System.out.println("Range Sum: " + result); // Output: 32
    }
}
