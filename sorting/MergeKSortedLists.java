import java.util.PriorityQueue;

/*
------------------------------------------------------------
Problem: Merge k Sorted Lists (LeetCode)

You are given an array of k linked lists, each sorted in ascending order.
Merge all the linked lists into one sorted linked list and return it.

Example:
Input: [1->4->5], [1->3->4], [2->6]
Output: 1->1->2->3->4->4->5->6

------------------------------------------------------------
How It Works:

We use a Min Heap (PriorityQueue) to always get the smallest element.

Steps:
1. Add the head of each linked list into the min heap.
2. Extract the smallest node from the heap.
3. Attach it to the result list.
4. If the extracted node has a next node, add it to the heap.
5. Repeat until the heap is empty.

This ensures we always build the sorted list efficiently.

------------------------------------------------------------
Time Complexity:
- O(N log k)
  where:
  N = total number of nodes
  k = number of lists

Each node is inserted and removed from the heap once,
and heap operations take O(log k).

------------------------------------------------------------
Space Complexity:
- O(k) for the heap

------------------------------------------------------------
*/

class MergeKSortedLists {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            (a, b) -> a.val - b.val
        );

        // Add all list heads
        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }

        // Dummy node to build result
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = tail.next;

            if (minNode.next != null) {
                pq.add(minNode.next);
            }
        }

        return dummy.next;
    }

    // Helper method to print list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] lists = {l1, l2, l3};

        ListNode result = mergeKLists(lists);
        printList(result);
    }
}
