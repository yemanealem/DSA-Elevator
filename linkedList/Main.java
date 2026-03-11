/*
 * LeetCode Problem: Intersection of Two Linked Lists
 * 
 * Question:
 * Given the heads of two singly linked lists, return the node where the two lists intersect.
 * If the two linked lists have no intersection at all, return null.
 * 
 * Notes:
 * - Intersection is by reference, not by value.
 * - The lists retain their original structure.
 *
 * How it works (Optimized Length-Align Method):
 * 1. Compute the lengths of both lists (lenA and lenB).
 * 2. Align the starting points by skipping the extra nodes in the longer list.
 * 3. Traverse both lists together one node at a time.
 * 4. If the nodes are the same by reference, return that node (intersection).
 * 5. If both pointers reach null, there is no intersection.
 *
 * Time Complexity: O(m + n), where m and n are lengths of the two lists.
 * Space Complexity: O(1), constant extra space.
 */

// Definition for singly-linked list node
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

// Intersection class containing the solution
class Intersection {

    // Function to find intersection node of two linked lists
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        // Step 1: Compute lengths of both lists
        int lenA = 0, lenB = 0;
        ListNode currA = headA, currB = headB;

        while (currA != null) { lenA++; currA = currA.next; }
        while (currB != null) { lenB++; currB = currB.next; }

        // Step 2: Align the start of both lists
        currA = headA;
        currB = headB;
        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; i++) currA = currA.next;
        } else {
            for (int i = 0; i < lenB - lenA; i++) currB = currB.next;
        }

        // Step 3: Traverse together until intersection is found
        while (currA != null && currB != null) {
            if (currA == currB) return currA; // Intersection found
            currA = currA.next;
            currB = currB.next;
        }

        return null; // No intersection
    }
}

// Main class to test Intersection
public class Main {
    public static void main(String[] args) {
        // Create intersection part: 8 -> 4 -> 5
        ListNode intersect = new ListNode(8);
        intersect.next = new ListNode(4);
        intersect.next.next = new ListNode(5);

        // List A: 4 -> 1 -> 8 -> 4 -> 5
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = intersect;

        // List B: 5 -> 6 -> 1 -> 8 -> 4 -> 5
        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = intersect;

        // Use Intersection class
        Intersection intersection = new Intersection();
        ListNode result = intersection.getIntersectionNode(headA, headB);

        // Output the result
        if (result != null) {
            System.out.println("Intersection at node with value: " + result.val);
        } else {
            System.out.println("No intersection found.");
        }
    }
}
