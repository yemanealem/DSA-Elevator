import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class LargestValuesInTreeRows {

    /*
     * QUESTION:
     * Given the root of a binary tree, return an array of the largest value in each row of the tree.
     *
     * HOW IT WORKS:
     * - Use Breadth-First Search (BFS) to traverse level by level.
     * - For each level:
     *     1. Get the size of the queue (number of nodes in that level)
     *     2. Traverse all nodes in that level
     *     3. Track the maximum value
     * - Add the max value of each level to the result list
     *
     * TIME COMPLEXITY:
     * O(n) → each node is visited onc
     *
     * SPACE COMPLEXITY:
     * O(w) → width of the tree (queue size in worst case)
     */

    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                max = Math.max(max, node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            result.add(max);
        }

        return result;
    }

    public static void main(String[] args) {
        /*
                1
               / \
              3   2
             / \   \
            5   3   9
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);

        List<Integer> result = largestValues(root);
        System.out.println(result); 
    }
}
