import java.util.*;

public class Codec {

    private static final String SEP = ",";
    private static final String NULL = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) {
        // Base case: null node
        if (node == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        // Preorder: Root -> Left -> Right
        sb.append(node.val).append(SEP);
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(SEP);
        Queue<String> queue = new LinkedList<>(Arrays.asList(nodes));
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Queue<String> queue) {
        String val = queue.poll();

        // If null marker, return null
        if (val.equals(NULL)) {
            return null;
        }

        // Create node
        TreeNode node = new TreeNode(Integer.parseInt(val));

        // Rebuild tree recursively (same preorder order)
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);

        return node;
    }
}
