/*
🧩 Problem: Sort List (LeetCode)

Given the head of a linked list, return the list after sorting it in ascending order.

Example:
Input: 4 -> 2 -> 1 -> 3
Output: 1 -> 2 -> 3 -> 4

------------------------------------------------------------

💡 How It Works (Merge Sort on Linked List):

We use Merge Sort because:
- Linked lists do NOT support random access
- Merge sort works efficiently with O(1) extra space for lists

Steps:
1. Find the middle of the list using slow & fast pointers
2. Split the list into two halves
3. Recursively sort both halves
4. Merge the two sorted halves

------------------------------------------------------------

🔍 Key Functions:

1. findMid:
   - Use slow & fast pointer to split list into two halves

2. merge:
   - Merge two sorted linked lists into one sorted list

------------------------------------------------------------

⏱️ Time Complexity:
O(n log n)
- We split the list log n times
- Each merge takes O(n)

📦 Space Complexity:
O(log n)
- Due to recursive call stack

------------------------------------------------------------

🎯 Why Merge Sort?
- Efficient for linked lists
- No need for extra array
- Stable sorting algorithm

------------------------------------------------------------
*/

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class SortList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // Step 1: Split list into halves
        ListNode mid = getMid(head);
        ListNode rightHead = mid.next;
        mid.next = null;

        // Step 2: Sort both halves
        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        // Step 3: Merge sorted halves
        return merge(left, right);
    }

    private ListNode getMid(ListNode head) {
        ListNode slow = head, fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        // Attach remaining nodes
        if (l1 != null) tail.next = l1;
        if (l2 != null) tail.next = l2;

        return dummy.next;
    }
}
