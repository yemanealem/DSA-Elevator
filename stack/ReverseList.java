class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;

        Stack<ListNode> stack = new Stack<>();

        // Push all nodes into stack
        ListNode current = head;
        while (current != null) {
            stack.push(current);
            current = current.next;
        }

        // Pop from stack and rebuild reversed list
        ListNode newHead = stack.pop();
        ListNode temp = newHead;

        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            temp.next = node;
            temp = node;
        }

        // End the reversed list
        temp.next = null;

        return newHead;
    }
}
