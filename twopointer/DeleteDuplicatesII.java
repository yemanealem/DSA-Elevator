class Solution {
    public ListNode deleteDuplicates(ListNode head) {

        // Edge case
        if (head == null) return null;

        // Dummy node to handle cases where head is duplicate
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;   // Last confirmed unique node
        ListNode curr = head;    // Traversal pointer

        while (curr != null) {

            // If duplicate sequence detected
            if (curr.next != null && curr.val == curr.next.val) {

                // Skip all nodes with same value
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }

                // Remove duplicates
                prev.next = curr.next;

            } else {
                // Move prev only if no duplicate
                prev = prev.next;
            }

            curr = curr.next;
        }

        return dummy.next;
    }
}
