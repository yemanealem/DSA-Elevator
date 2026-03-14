/**
 * Definition for singly-linked list.
 */
class ListNode {
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

public class MergeTwoLists {

    /*
    ---------------------------------------------------------
    Merge Two Sorted Lists (Recursive)
    ---------------------------------------------------------

    Question:
    Given two sorted linked lists, merge them into one
    sorted linked list and return the head.

    ---------------------------------------------------------
    How it works step-by-step:
    ---------------------------------------------------------

    1. Base Case:
       - If list1 is null → return list2
       - If list2 is null → return list1

    2. Compare values:
       - If list1.val < list2.val:
             list1.next = mergeTwoLists(list1.next, list2)
             return list1
       - Else:
             list2.next = mergeTwoLists(list1, list2.next)
             return list2

    Each recursive call reduces the problem size.
    Eventually one list becomes null.

    Time Complexity: O(n + m)
    Space Complexity: O(n + m) (recursion stack)
    ---------------------------------------------------------
    */

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // Base cases
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        // Recursive merge
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    // Helper method to print linked list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    // Main method
    public static void main(String[] args) {

        // List 1: 1 -> 3 -> 5
        ListNode list1 = new ListNode(1,
                            new ListNode(3,
                            new ListNode(5)));

        // List 2: 2 -> 4 -> 6
        ListNode list2 = new ListNode(2,
                            new ListNode(4,
                            new ListNode(6)));

        // Merge
        ListNode merged = mergeTwoLists(list1, list2);

        // Print result
        System.out.println("Merged Sorted List:");
        printList(merged);
    }
}
