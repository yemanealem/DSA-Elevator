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

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode deleteDuplicates(ListNode head) {

        if (head == null) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode curr = head;

        /*
        Trace for Example: 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5

        Initial:
        dummy -> 0 -> 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
        prev = dummy, curr = 1

        Step 1:
        curr = 1
        curr.next.val = 2 (not equal)
        -> move prev = 1, curr = 2

        Step 2:
        curr = 2
        curr.next.val = 3 (not equal)
        -> move prev = 2, curr = 3

        Step 3:
        curr = 3
        curr.next.val = 3 (duplicate detected)
        -> skip all 3s: curr = 3 (second 3)
        -> prev.next = curr.next = 4
        List now: 0 -> 1 -> 2 -> 4 -> 4 -> 5
        curr = 4

        Step 4:
        curr = 4
        curr.next.val = 4 (duplicate detected)
        -> skip all 4s: curr = 4 (second 4)
        -> prev.next = curr.next = 5
        List now: 0 -> 1 -> 2 -> 5
        curr = 5

        Step 5:
        curr = 5
        curr.next = null (no duplicate)
        -> move prev = 5, curr = null

        Done.
        Final List: 1 -> 2 -> 5
        */

        while (curr != null) {

            if (curr.next != null && curr.val == curr.next.val) {
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next; // skip duplicates
                }
                prev.next = curr.next; // remove duplicates
            } else {
                prev = prev.next; // move prev if unique
            }

            curr = curr.next;
        }

        return dummy.next;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

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
    }
}
