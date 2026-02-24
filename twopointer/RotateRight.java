public class RotateRight {

    // ----------------------------------------------------------------------
    // Question:
    // Given the head of a singly linked list, rotate the list to the right by k places.
    //
    // Example:
    // Input:  1 -> 2 -> 3 -> 4 -> 5, k = 2
    // Output: 4 -> 5 -> 1 -> 2 -> 3
    // ----------------------------------------------------------------------

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode rotateRight(ListNode head, int k) {

        // Edge cases
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // ------------------------------------------------------------------
        // How it works:
        // 1. Find the length of the list and the last node (tail).
        // 2. Connect tail -> head to form a circular list.
        // 3. Reduce k using (k % length) because rotating full cycles has no effect.
        // 4. Find the new tail at position (length - k).
        // 5. Break the circular link and return the new head.
        // ------------------------------------------------------------------

        // Step 1: Find length and tail
        ListNode tail = head;
        int length = 1;

        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Step 2: Reduce k
        k = k % length;
        if (k == 0) {
            return head;
        }

        // Step 3: Make circular
        tail.next = head;

        // Step 4: Find new tail
        int stepsToNewTail = length - k;
        ListNode newTail = head;

        for (int i = 1; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        // Step 5: Set new head and break circle
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }

    // Helper method to print list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {

        // Create list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5)))));

        System.out.println("Original List:");
        printList(head);

        ListNode rotated = rotateRight(head, 2);

        System.out.println("Rotated List:");
        printList(rotated);
    }
}
