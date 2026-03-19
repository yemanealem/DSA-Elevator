/*
Problem: Linked List Cycle II (LeetCode #142)

Description:
Given the head of a linked list, return the node where the cycle begins.
If there is no cycle, return null.

Example:
Input: 3 -> 2 -> 0 -> -4, cycle at node 2
Output: Node with value 2

Approach: Floyd’s Tortoise and Hare (Fast & Slow Pointers)

Step-by-step Explanation:

1. Initialize:
   slow = head (3)
   fast = head (3)

2. Move slow by 1 step, fast by 2 steps until they meet:

Iteration Trace (slow / fast):
- Step 1: slow=2, fast=0
- Step 2: slow=0, fast=2
- Step 3: slow=-4, fast=-4  <-- They meet here, cycle detected

3. Find the start of the cycle:
   - Initialize pointer = head (3)
   - Move pointer and slow one step at a time:

Trace to cycle start:
- Step 1: pointer=3, slow=-4
- Step 2: pointer=2, slow=2  <-- They meet at node with value 2

4. Return the node where they meet: node with value 2 (start of the cycle)

Time Complexity: O(n)  -> Traverse each node at most once
Space Complexity: O(1) -> Constant space
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
            slow = slow.next;           // slow moves 1 step
            fast = fast.next.next;      // fast moves 2 steps

            if (slow == fast) {         // pointers meet → cycle detected
                hasCycle = true;
                break;
            }
        }

        // Step 2: If cycle exists, find entry point
        if (hasCycle) {
            ListNode pointer = head;    // start from head
            while (pointer != slow) {   // move both 1 step at a time
                pointer = pointer.next;
                slow = slow.next;
            }
            return pointer;             // start of the cycle
        }

        return null;                    // no cycle
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
