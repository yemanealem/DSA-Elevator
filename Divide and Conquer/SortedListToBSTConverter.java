/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * Converts a sorted singly linked list to a height-balanced BST
 * and contains the main method for testing
 */
public class SortedListToBSTConverter {

    // Method to convert sorted linked list to BST
    public TreeNode convert(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        if (prev != null) prev.next = null;
        
        TreeNode root = new TreeNode(slow.val);
        root.left = convert(head);
        root.right = convert(slow.next);
        
        return root;
    }
    
    // Helper method to print BST inorder
    public void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    // Main method for testing
    public static void main(String[] args) {
        // Create a sorted linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        
        // Convert the list to BST
        SortedListToBSTConverter converter = new SortedListToBSTConverter();
        TreeNode bstRoot = converter.convert(head);
        
        // Print inorder traversal
        System.out.println("Inorder traversal of BST:");
        converter.printInorder(bstRoot); // Output: 1 2 3 4 5
    }
}
