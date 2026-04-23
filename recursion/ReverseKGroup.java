public class ReverseKGroup {

    // Definition for singly-linked list
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    // Recursive function
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;

        // Step 1: Check if k nodes exist
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }

        if (count == k) {
            ListNode prev = null;
            curr = head;

            for (int i = 0; i < k; i++) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            head.next = reverseKGroup(curr, k);

            return prev;
        }

        return head;
    }

    // Helper to print list
    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    // Main method
    public static void main(String[] args) {
        ReverseKGroup obj = new ReverseKGroup();

        // Create list: 1 → 2 → 3 → 4 → 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int k = 2;

        System.out.println("Original List:");
        obj.printList(head);

        head = obj.reverseKGroup(head, k);

        System.out.println("After Reversing in k-group:");
        obj.printList(head);
    }
}
