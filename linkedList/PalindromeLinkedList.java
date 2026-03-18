import java.util.Arrays;

/*
 * Problem:
 * Determine whether a singly linked list is a palindrome.
 *
 * Approach:
 * 1. Use slow and fast pointers to find the middle.
 * 2. Reverse the second half of the list.
 * 3. Compare the first half and second half.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class PalindromeLinkedList {

    public static boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) {
            return true;
        }

        // Step 1: Find middle
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse second half
        ListNode secondHalf = reverse(slow.next);
        slow.next = null; // break list

        // Step 3: Compare both halves
        ListNode firstHalf = head;

        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return true;
    }

    // Helper method to reverse linked list
    private static ListNode reverse(ListNode head) {
        ListNode prev = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }

    // Main method for testing
    public static void main(String[] args) {

        // Example: 1 -> 2 -> 2 -> 1
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);

        boolean result = isPalindrome(head);

        System.out.println("Is Palindrome? " + result);
    }
}
