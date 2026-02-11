class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class AddTwoNumber {

    // Main add method (calls recursive helper)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return add(l1, l2, 0);
    }

    // Recursive helper
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

        // Create current node
        ListNode node = new ListNode(sum % 10);

        // Recursive call
        node.next = add(l1, l2, sum / 10);

        return node;
    }

    // Utility method to print linked list
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

    // MAIN METHOD
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

        // Add numbers
        ListNode result = solution.addTwoNumbers(l1, l2);

        System.out.println("Result:");
        printList(result);
    }
}
