/*
Problem: Middle of the Linked List (LeetCode #876)

Description:
Given a non-empty singly linked list, return the middle node of the linked list.
If there are two middle nodes, return the second middle node.

Example 1:
Input: head = [1,2,3,4,5]
Output: Node with value 3
Explanation: The middle node is 3.

Example 2:
Input: head = [1,2,3,4,5,6]
Output: Node with value 4
Explanation: There are two middle nodes (3 and 4), return the second one (4).

Approach: Fast and Slow Pointers
- Use two pointers, slow and fast, both starting at head.
- Move slow by 1 step, fast by 2 steps.
- When fast reaches the end of the list, slow will be at the middle.
- This works for both even and odd length lists.

Time Complexity: O(n)  -> traverse each node at most once
Space Complexity: O(1) -> no extra space used
*/

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

class MiddleNode {
    // Method to find the middle node
    public ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Move fast 2 steps and slow 1 step at a time
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow; // slow points to the middle node
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a sample linked list: 1 -> 2 -> 3 -> 4 -> 5 -> 6
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        // Create an instance of MiddleNode
        MiddleNode middleFinder = new MiddleNode();

        // Find the middle node
        ListNode middle = middleFinder.findMiddle(head);

        // Print the value of the middle node
        System.out.println("Middle node value: " + middle.val);
    }
}
