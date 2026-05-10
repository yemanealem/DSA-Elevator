import java.util.*;

/*
LeetCode 95. Unique Binary Search Trees II

Problem:
Generate all structurally unique BSTs
(Binary Search Trees) storing values 1 to n.

----------------------------------------------------
OPTIMIZED DYNAMIC PROGRAMMING SOLUTION
----------------------------------------------------

Why This Version Is Faster:
1. Uses 2D DP cache instead of HashMap<String,...>
2. Avoids expensive String creation
3. Faster memo lookup
4. Uses constructor directly:
      new TreeNode(root, left, right)

----------------------------------------------------
HOW IT WORKS
----------------------------------------------------

For every value i:
- Choose i as root
- Generate all left subtrees
- Generate all right subtrees
- Combine every possibility

Example:

n = 3

Possible roots:
1, 2, 3

Each root recursively builds:
left subtree + right subtree

----------------------------------------------------
WHY THIS IS DYNAMIC PROGRAMMING
----------------------------------------------------

Subproblems repeat many times.

Example:
build(1,2) may be called repeatedly.

So we cache:
memo[start][end]

----------------------------------------------------
TIME COMPLEXITY
----------------------------------------------------

Catalan Number Complexity

Approximately:
O(4^n / sqrt(n))

This is optimal because we must generate
all unique BSTs.

----------------------------------------------------
SPACE COMPLEXITY
----------------------------------------------------

O(number of generated trees)

----------------------------------------------------
*/

public class UniqueBinarySearchTreesII {

    // TreeNode definition
    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(
                int val,
                TreeNode left,
                TreeNode right
        ) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // DP memo cache
    private List<TreeNode>[][] memo;

    // Main API
    public List<TreeNode> generateTrees(int n) {

        if (n == 0) {
            return new ArrayList<>();
        }

        // Initialize DP cache
        memo = new ArrayList[n + 1][n + 1];

        return build(1, n);
    }

    // Generate BSTs in range [start, end]
    private List<TreeNode> build(int start, int end) {

        // Empty subtree
        if (start > end) {

            List<TreeNode> base =
                    new ArrayList<>();

            base.add(null);

            return base;
        }

        // Return cached result
        if (memo[start][end] != null) {
            return memo[start][end];
        }

        List<TreeNode> result =
                new ArrayList<>();

        // Try every number as root
        for (int root = start;
             root <= end;
             root++) {

            // Generate left subtrees
            List<TreeNode> leftTrees =
                    build(start, root - 1);

            // Generate right subtrees
            List<TreeNode> rightTrees =
                    build(root + 1, end);

            // Combine all possibilities
            for (TreeNode left : leftTrees) {

                for (TreeNode right : rightTrees) {

                    result.add(
                            new TreeNode(
                                    root,
                                    left,
                                    right
                            )
                    );
                }
            }
        }

        memo[start][end] = result;

        return result;
    }

    private static void preorder(TreeNode root) {

        if (root == null) {
            System.out.print("null ");
            return;
        }

        System.out.print(root.val + " ");

        preorder(root.left);

        preorder(root.right);
    }

  
    public static void main(String[] args) {

        UniqueBinarySearchTreesII solution =
                new UniqueBinarySearchTreesII();

        int n = 3;

        List<TreeNode> trees =
                solution.generateTrees(n);

        System.out.println(
                "Total Unique BSTs = "
                        + trees.size()
        );

        System.out.println();

        
        for (TreeNode root : trees) {

            preorder(root);

            System.out.println();
        }
    }
}