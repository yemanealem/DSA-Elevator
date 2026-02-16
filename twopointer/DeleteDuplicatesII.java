/*
===========================================================
Class: DeleteDuplicatesII

Problem:
Given the head of a sorted linked list, delete all nodes
that have duplicate numbers, leaving only distinct numbers
from the original list.

Example:
Input:  1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
Output: 1 -> 2 -> 5

Approach:
1. Use a dummy node to handle cases where head is a duplicate.
2. Use two pointers:
   - prev (last confirmed unique node)
   - curr (traversal pointer)
3. If duplicates are detected, skip the entire block.
4. Move prev only when current node is unique.

Time Complexity: O(n)
Space Complexity: O(1)
===========================================================
*/

public class DeleteDuplicatesII {

    // Definition for singly-linked list
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // Method to remove duplicates
    public static ListNode deleteDuplicates(ListNode head) {

        if (head == null) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy; // last unique node
        ListNode curr = head;  // pointer to traverse

        while (curr != null) {

            // If duplicates detected
            if (curr.next != null && curr.val == curr.next.val) {

                // Skip all nodes with same value
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }

                // Remove duplicates
                prev.next = curr.next;

            } else {
                // Move prev only if current is unique
                prev = prev.next;
            }

            curr = curr.next;
        }

        return dummy.next;
    }

    // Helper method to print the linked list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    // Main method to test the solution
    public static void main(String[] args) {

        // Example 1: 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
        ListNode head = new ListNode(1,
                        new ListNode(2,
                        new ListNode(3,
                        new ListNode(3,
                        new ListNode(4,
                        new ListNode(4,
                        new ListNode(5)))))));

        System.out.println("Original List:");
        printList(head);

        ListNode result = deleteDuplicates(head);

        System.out.println("After Removing Duplicates:");
        printList(result);

        // Example 2: 1 -> 1 -> 1 -> 2 -> 3
        ListNode head2 = new ListNode(1,
                        new ListNode(1,
                        new ListNode(1,
                        new ListNode(2,
                        new ListNode(3)))));

        System.out.println("\nOriginal List:");
        printList(head2);

        ListNode result2 = deleteDuplicates(head2);

        System.out.println("After Removing Duplicates:");
        printList(result2);
    }
}
