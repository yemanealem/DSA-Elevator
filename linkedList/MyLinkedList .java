/**
 * LeetCode 707 - Design Linked List
 * 
 * Problem:
 * Design a singly linked list with the following operations:
 * 1. get(index) - Get the value of the index-th node in the linked list. If the index is invalid, return -1.
 * 2. addAtHead(val) - Add a node of value val before the first element of the linked list.
 * 3. addAtTail(val) - Append a node of value val to the last element of the linked list.
 * 4. addAtIndex(index, val) - Add a node of value val before the index-th node in the linked list. 
 *    If index equals the length of the linked list, the node will be appended at the end. 
 *    If index is greater than the length, do nothing.
 * 5. deleteAtIndex(index) - Delete the index-th node if the index is valid.
 * 
 * Approach:
 * - Singly linked list with head and tail pointer.
 * - Maintain size to check index validity efficiently.
 * - Optimized addAtTail to O(1) using tail pointer.
 * 
 * Time Complexity:
 * - get(index): O(n)
 * - addAtHead: O(1)
 * - addAtTail: O(1)
 * - addAtIndex: O(n) in worst case
 * - deleteAtIndex: O(n) in worst case
 */

class MyLinkedList {

    // Node class
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private ListNode head;
    private ListNode tail; // optimized tail pointer
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        ListNode curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.val;
    }
    
    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head;
        head = newNode;
        if (size == 0) tail = newNode;
        size++;
    }
    
    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        if (size == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;
        if (index == 0) {
            addAtHead(val);
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }
        ListNode curr = head;
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }
        ListNode newNode = new ListNode(val);
        newNode.next = curr.next;
        curr.next = newNode;
        size++;
    }
    
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;
        if (index == 0) {
            head = head.next;
            if (size == 1) tail = null;
        } else {
            ListNode curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.next;
            }
            curr.next = curr.next.next;
            if (index == size - 1) tail = curr;
        }
        size--;
    }

    // Helper method to print the linked list
    public void printList() {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    // Main method to test the linked list
    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();

        // Add elements
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1, 2);   // linked list becomes 1->2->3
        linkedList.printList();

        // Get element
        System.out.println("Get index 1: " + linkedList.get(1)); // 2

        // Delete element
        linkedList.deleteAtIndex(1);   // linked list becomes 1->3
        linkedList.printList();

        // Get element
        System.out.println("Get index 1: " + linkedList.get(1)); // 3

        // Add at head
        linkedList.addAtHead(0);       // linked list becomes 0->1->3
        linkedList.printList();

        // Add at tail
        linkedList.addAtTail(4);       // linked list becomes 0->1->3->4
        linkedList.printList();
    }
}
