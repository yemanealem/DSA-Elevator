// Question:
// Reverse a singly linked list.
// Given the head of a linked list, reverse the list and return the new head.
//
// How it Works (Algorithm):
// - Use three pointers: prev, current, and nextNode.
// - 'current' traverses the original list.
// - Store current.next in nextNode before breaking link.
// - Reverse the link: current.next = prev.
// - Move prev and current one step forward.
// - Repeat until current becomes null.
// - Finally, prev becomes the new head.

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class ReverseList {

    // Function to reverse linked list
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextNode = current.next; // store next node
            current.next = prev;              // reverse link
            prev = current;                   // move prev forward
            current = nextNode;               // move current forward
        }

        return prev; // new head of reversed list
    }

    // Function to print linked list
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // Creating linked list: 1 -> 2 -> 3 -> 4 -> null
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        System.out.println("Original List:");
        printList(head);

        // Reversing the list
        ReverseList solution = new ReverseList();
        ListNode reversed = solution.reverseList(head);

        System.out.println("Reversed List:");
        printList(reversed);
    }
}
