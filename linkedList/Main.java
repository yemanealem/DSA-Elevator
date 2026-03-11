// Definition of a singly-linked list node
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

// Solution class containing the intersection logic
class Solution {

    // Function to find intersection node of two linked lists
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        // Step 1: Compute lengths of both lists
        int lenA = 0, lenB = 0;
        ListNode currA = headA, currB = headB;

        while (currA != null) {
            lenA++;
            currA = currA.next;
        }

        while (currB != null) {
            lenB++;
            currB = currB.next;
        }

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

// Main class to run and test the program
public class Main {
    public static void main(String[] args) {
        // Create intersection part
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

        Solution solution = new Solution();
        ListNode result = solution.getIntersectionNode(headA, headB);

        if (result != null) {
            System.out.println("Intersection at node with value: " + result.val);
        } else {
            System.out.println("No intersection found.");
        }
    }
}
