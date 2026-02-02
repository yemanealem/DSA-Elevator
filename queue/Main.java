/*
LeetCode 232 - Implement Queue using Stacks

QUESTION:
Implement a queue using stacks. The queue should support:
- push(x)   -> add element to the back
- pop()     -> remove element from the front
- peek()    -> get front element
- empty()   -> check if queue is empty

Rules:
- Only standard stack operations are allowed: push, pop, peek, isEmpty
- FIFO behavior must be maintained
*/

import java.util.Stack;

class QueueUsingStack {

    // Two stacks: stack1 for push, stack2 for pop
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    /*
    HOW IT WORKS:

    1. push(x):
       - Push element into stack1
       - New elements are always in stack1

    2. pop() / peek():
       - If stack2 is empty:
           Move all elements from stack1 to stack2 (reverses order)
       - Pop / peek from stack2
       - This ensures FIFO behavior

    3. empty():
       - Queue is empty if both stacks are empty

    TIME COMPLEXITY:
    - push: O(1)
    - pop / peek: O(1) amortized
    - empty: O(1)
    */

    public QueueUsingStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    // Add element to the back of the queue
    public void push(int x) {
        stack1.push(x);
    }

    // Remove the element from the front
    public int pop() {
        shiftStacks();
        return stack2.pop();
    }

    // Get the front element
    public int peek() {
        shiftStacks();
        return stack2.peek();
    }

    // Check if queue is empty
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    // Move elements from stack1 to stack2 if needed
    private void shiftStacks() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }
}

// Main class to test QueueUsingStack
public class Main {

    public static void main(String[] args) {

        QueueUsingStack queue = new QueueUsingStack();

        // Add elements
        queue.push(10);
        queue.push(20);
        queue.push(30);

        // Pop elements
        System.out.println(queue.pop());  // 10
        System.out.println(queue.pop());  // 20

        // Peek at front element
        System.out.println(queue.peek()); // 30

        // Check if empty
        System.out.println(queue.empty()); // false

        // Add another element
        queue.push(40);
        System.out.println(queue.peek()); // 30

        // Pop remaining elements
        System.out.println(queue.pop());  // 30
        System.out.println(queue.pop());  // 40

        // Now queue is empty
        System.out.println(queue.empty()); // true
    }
}
