/*
Problem: Linked List Cycle II (LeetCode #142)

Description:
Given the head of a linked list, return the node where the cycle begins. 
If there is no cycle, return null.

A cycle exists if there is a node in the list that can be reached again by continuously following the next pointer.
Do not modify the linked list.

Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: Node with value 2
Explanation: There is a cycle in the linked list, where the tail connects to the 2nd node.

Example 2:
Input: head = [1,2], pos = 0
Output: Node with value 1
Explanation: The tail connects to the 1st node.

Example 3:
Input: head = [1], pos = -1
Output: null
Explanation: No cycle in the list.

Approach: Floyd’s Tortoise and Hare (Fast & Slow Pointers)
1. Use two pointers, slow and fast.
2. Move slow by 1 step, fast by 2 steps.
3. If they meet, there is a cycle.
4. To find the entry point:
   - Start a new pointer from head.
   - Move both this pointer and slow one step at a time.
   - The node where they meet is the start of the cycle.

Time Complexity: O(n) -> Each pointer traverses at most n nodes
Space Complexity: O(1) -> No extra space used
*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        // Step 1: Detect if a cycle exists
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        // Step 2: If there is a cycle, find the entry point
        if (hasCycle) {
            ListNode pointer = head;
            while (pointer != slow) {
                pointer = pointer.next;
                slow = slow.next;
            }
            return pointer; // start of the cycle
        }

        return null; // no cycle
    }
}

public class Main {
    public static void main(String[] args) {
        // Example: 3 -> 2 -> 0 -> -4, cycle at node 2
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next; // create cycle

        LinkedListCycleII solution = new LinkedListCycleII();
        ListNode cycleStart = solution.detectCycle(head);

        if (cycleStart != null) {
            System.out.println("Cycle starts at node with value: " + cycleStart.val);
        } else {
            System.out.println("No cycle detected.");
        }
    }
}
