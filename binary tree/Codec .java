import java.util.*;

/**
 * Problem: Serialize and Deserialize Binary Tree
 *
 * -----------------------------------------------
 * 🧠 Problem Description:
 * -----------------------------------------------
 * Design an algorithm to convert a binary tree into a string
 * (serialization) and then convert that string back to the exact
 * same binary tree (deserialization).
 *
 * Example:
 *        1
 *       / \
 *      2   3
 *         / \
 *        4   5
 *
 * Serialized output:
 * 1,2,#,#,3,4,#,#,5,#,#
 *
 * "#" represents a null node.
 *
 * -----------------------------------------------
 * 🧠 How it works:
 * -----------------------------------------------
 * We use PREORDER traversal (Root → Left → Right):
 *
 * 1. Serialize:
 *    - Visit node
 *    - If null → add "#"
 *    - Otherwise → add value
 *    - Recursively go left and right
 *
 *    This preserves structure using null markers.
 *
 * 2. Deserialize:
 *    - Read values in same preorder order
 *    - If value is "#", return null
 *    - Otherwise, create node
 *    - Recursively build left and right subtree
 *
 * Key idea:
 * → Same traversal order guarantees correct reconstruction.
 *
 * -----------------------------------------------
 * ⏱️ Running Time:
 * -----------------------------------------------
 * Serialization:
 *    Time:  O(n)  → visit every node once
 *    Space: O(n)  → output string + recursion stack
 *
 * Deserialization:
 *    Time:  O(n)  → process each token once
 *    Space: O(n)  → queue + recursion stack
 *
 * Overall:
 *    Time Complexity:  O(n)
 *    Space Complexity: O(n)
 */

public class Codec {

    private static final String SEP = ",";
    private static final String NULL = "#";

    // ---------------------------------------------------
    // Serialize Tree → String
    // ---------------------------------------------------
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        sb.append(node.val).append(SEP);
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }

   
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(SEP);
        Queue<String> queue = new LinkedList<>(Arrays.asList(nodes));
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Queue<String> queue) {
        String val = queue.poll();

        if (val.equals(NULL)) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(val));

        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);

        return node;
    }
}
