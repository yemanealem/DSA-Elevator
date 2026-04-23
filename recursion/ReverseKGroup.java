/**
 * Problem: Reverse Nodes in k-Group
 *
 * Given a linked list, reverse the nodes of the list k at a time and return the modified list.
 *
 * Rules:
 * 1. Only reverse if there are at least k nodes.
 * 2. If remaining nodes are less than k → leave them as is.
 *
 * Example:
 * Input:  1 → 2 → 3 → 4 → 5, k = 2
 * Output: 2 → 1 → 4 → 3 → 5
 */

public class ReverseKGroup {

    // Definition of ListNode
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    /**
     * Function: reverseKGroup
     *
     * This function reverses nodes in groups of k using recursion.
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        // Step 1: Check if there are at least k nodes
        ListNode curr = head;
        int count = 0;

        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }

        /**
         * If we have k nodes:
         * - Reverse them
         * - Recursively process the remaining list
         */
        if (count == k) {

            // Step 2: Reverse k nodes
            ListNode prev = null;
            curr = head;

            for (int i = 0; i < k; i++) {
                ListNode next = curr.next; // store next node
                curr.next = prev;          // reverse link
                prev = curr;               // move prev forward
                curr = next;               // move curr forward
            }

            /**
             * Step 3: Recursive call
             *
             * head is now the LAST node of reversed group
             * So connect it with the result of next recursion
             */
            head.next = reverseKGroup(curr, k);

            return prev;
        }

        return head;
    }

    
    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }


    public static void main(String[] args) {
        ReverseKGroup obj = new ReverseKGroup();

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
