/*
 * LeetCode Problem: Remove Nth Node From End of List
 * 
 * Question:
 * Given the head of a singly linked list, remove the nth node from the end of the list
 * and return its head.
 *
 * How it works (Two-pointer approach):
 * 1. Initialize two pointers, fast and slow, at a dummy node before head.
 * 2. Move fast pointer n+1 steps ahead (dummy included).
 * 3. Move both pointers together until fast reaches the end.
 * 4. slow.next is the node to remove. Adjust pointers to skip it.
 *
 * Time Complexity: O(L), one pass through the list (L = length of list)
 * Space Complexity: O(1), constant extra space
 */

// Definition for singly-linked list node
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

// Main class containing solution and test
public class RemoveNthNode {

    /**
     * Removes the nth node from the end of the list
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node to handle edge cases (removing first node)
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // Step 1: Move fast pointer n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Step 2: Move both pointers together until fast reaches the end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Step 3: Remove the target node
        slow.next = slow.next.next;

        return dummy.next; // Return new head
    }

    // Main method to test the solution
    public static void main(String[] args) {
        // Create linked list: 1->2->3->4->5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int n = 2; // Remove 2nd node from end

        // Use RemoveNthNode class
        RemoveNthNode remover = new RemoveNthNode();
        ListNode newHead = remover.removeNthFromEnd(head, n);

        // Print the modified list
        System.out.print("Modified List: ");
        ListNode curr = newHead;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print("->");
            curr = curr.next;
        }
    }
}
