/*
LeetCode #2. Add Two Numbers

You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order, and each of their nodes contains a single digit.
Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero,
except the number 0 itself.

Example 1:
Input:  l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]

Explanation:
342 + 465 = 807


-----------------------------------------
HOW RECURSIVE SOLUTION WORKS (TRACE)
-----------------------------------------

Example:
l1 = 2 -> 4 -> 3
l2 = 5 -> 6 -> 4

Call 1:
add(2,5,0)
sum = 2 + 5 + 0 = 7
node = 7
carry = 0
→ recursive call add(4,6,0)

Call 2:
add(4,6,0)
sum = 4 + 6 + 0 = 10
node = 0
carry = 1
→ recursive call add(3,4,1)

Call 3:
add(3,4,1)
sum = 3 + 4 + 1 = 8
node = 8
carry = 0
→ recursive call add(null,null,0)

Call 4 (Base Case):
l1 = null, l2 = null, carry = 0
return null

Now recursion builds result backward:

8 -> null
0 -> 8
7 -> 0 -> 8

Final Result:
7 -> 0 -> 8


Time Complexity: O(max(n, m))
Space Complexity: O(max(n, m))   (because of recursion stack)
*/

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class AddTwoNumber {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return add(l1, l2, 0);
    }

    private ListNode add(ListNode l1, ListNode l2, int carry) {

        // Base case
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        int sum = carry;

        if (l1 != null) {
            sum += l1.val;
            l1 = l1.next;
        }

        if (l2 != null) {
            sum += l2.val;
            l2 = l2.next;
        }

        // Create node with last digit
        ListNode node = new ListNode(sum % 10);

        // Recursive call with next nodes and carry
        node.next = add(l1, l2, sum / 10);

        return node;
    }

    // Utility method to print list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        AddTwoNumber solution = new AddTwoNumber();

        // Create first number: 2 -> 4 -> 3
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        // Create second number: 5 -> 6 -> 4
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        System.out.println("First Number:");
        printList(l1);

        System.out.println("Second Number:");
        printList(l2);

        ListNode result = solution.addTwoNumbers(l1, l2);

        System.out.println("Result:");
        printList(result);
    }
}
