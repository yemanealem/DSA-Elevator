/**
 * Problem: Populating Next Right Pointers in Each Node II
 *
 * Given a binary tree, populate each next pointer to point
 * to its next right node. If there is no next right node,
 * set next to null.
 *
 * Approach:
 * - Use level-by-level traversal (without extra space)
 * - Use a dummy node to connect next level nodes
 * - Traverse current level using next pointers
 *
 * Time: O(n)
 * Space: O(1)
 */

class Node {
    int val;
    Node left, right, next;

    Node(int val) {
        this.val = val;
    }
}

public class ConnectNextRightPointers {

    public Node connect(Node root) {
        if (root == null) return null;

        Node current = root;

        while (current != null) {
            Node dummy = new Node(0); // start of next level
            Node tail = dummy;

            // Traverse current level
            while (current != null) {
                if (current.left != null) {
                    tail.next = current.left;
                    tail = tail.next;
                }

                if (current.right != null) {
                    tail.next = current.right;
                    tail = tail.next;
                }

                current = current.next;
            }

            // Move to next level
            current = dummy.next;
        }

        return root;
    }

    // Helper method to print levels using next pointers
    public static void printLevels(Node root) {
        Node levelStart = root;

        while (levelStart != null) {
            Node current = levelStart;
            levelStart = null;

            while (current != null) {
                System.out.print(current.val + " -> ");

                if (levelStart == null) {
                    if (current.left != null) levelStart = current.left;
                    else if (current.right != null) levelStart = current.right;
                }

                current = current.next;
            }

            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        ConnectNextRightPointers sol = new ConnectNextRightPointers();

        // Build sample tree:
        //        1
        //      /   \
        //     2     3
        //    / \     \
        //   4   5     7

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);

        sol.connect(root);

        System.out.println("Level order using next pointers:");
        printLevels(root);
    }
}
