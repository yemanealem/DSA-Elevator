import java.util.*;

/*
LeetCode 95. Unique Binary Search Trees II

Problem:
Generate all structurally unique BSTs
that store values 1 to n.

----------------------------------------------------
KEY IDEA
----------------------------------------------------

For every number i:
- Use i as root
- Generate all left subtrees from:
      start -> i-1
- Generate all right subtrees from:
      i+1 -> end

Then combine every:
(left subtree) × (right subtree)

----------------------------------------------------
WHY THIS IS DYNAMIC PROGRAMMING
----------------------------------------------------

Subproblems repeat many times.

Example:
generate(1,3)

Needs:
generate(1,1)
generate(3,3)

Later these ranges are needed again.

So we cache results using memoization.

----------------------------------------------------
TIME COMPLEXITY
----------------------------------------------------

Catalan Number Complexity

Approximately:
O(4^n / sqrt(n))

This is expected because the number of
unique BSTs itself grows exponentially.

----------------------------------------------------
SPACE COMPLEXITY
----------------------------------------------------

O(number of generated trees)

Plus recursion stack and memo cache.

----------------------------------------------------
OPTIMIZATIONS
----------------------------------------------------

1. Memoization avoids recomputation
2. HashMap caching
3. Reuse subtree results
4. Efficient recursive construction

----------------------------------------------------
*/

public class UniqueBinarySearchTreesII {

    // TreeNode definition
    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // Memoization cache
    private static final Map<String, List<TreeNode>> memo =
            new HashMap<>();

    public static List<TreeNode> generateTrees(int n) {

        if (n == 0) {
            return new ArrayList<>();
        }

        return build(1, n);
    }

    // Build all BSTs in range [start, end]
    private static List<TreeNode> build(int start, int end) {

        String key = start + "," + end;

        // Return cached result
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        List<TreeNode> result = new ArrayList<>();

        // Base case
        if (start > end) {

            result.add(null);

            memo.put(key, result);

            return result;
        }

        // Try every value as root
        for (int rootValue = start;
             rootValue <= end;
             rootValue++) {

            // Generate left subtrees
            List<TreeNode> leftTrees =
                    build(start, rootValue - 1);

            // Generate right subtrees
            List<TreeNode> rightTrees =
                    build(rootValue + 1, end);

            // Combine all possibilities
            for (TreeNode left : leftTrees) {

                for (TreeNode right : rightTrees) {

                    TreeNode root =
                            new TreeNode(rootValue);

                    root.left = left;
                    root.right = right;

                    result.add(root);
                }
            }
        }

        // Cache result
        memo.put(key, result);

        return result;
    }

    // Preorder traversal for testing
    private static void preorder(TreeNode root) {

        if (root == null) {
            System.out.print("null ");
            return;
        }

        System.out.print(root.val + " ");

        preorder(root.left);
        preorder(root.right);
    }

    // Main method
    public static void main(String[] args) {

        int n = 3;

        List<TreeNode> trees = generateTrees(n);

        System.out.println(
                "Total Unique BSTs: " + trees.size()
        );

        for (TreeNode tree : trees) {

            preorder(tree);

            System.out.println();
        }
    }
}