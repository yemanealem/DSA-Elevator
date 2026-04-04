vimport java.util.PriorityQueue;
import java.util.Collections;

/*
LeetCode: Last Stone Weight

QUESTION:
You are given an array of integers stones where stones[i] is the weight of the ith stone.

We are playing a game:
- Each turn, we choose the two heaviest stones and smash them together.
- If both stones have the same weight, both are destroyed.
- If they have different weights, the smaller one is destroyed, and the larger one becomes (difference of weights).

Continue this process until there is at most one stone left.

Return the weight of the last remaining stone.
If no stones remain, return 0.

------------------------------------------------------

HOW IT WORKS (HEAP / PRIORITY QUEUE APPROACH):

1. We always need the TWO LARGEST stones.
2. A Max Heap helps us efficiently get the largest values.
3. Java PriorityQueue is a MIN heap by default,
   so we use Collections.reverseOrder() to simulate a MAX heap.

STEPS:
- Insert all stones into the max heap.
- While heap size > 1:
    • Remove the largest (first)
    • Remove the second largest (second)
    • If they are not equal:
        → Insert (first - second) back into heap
- At the end:
    • If heap is empty → return 0
    • Else → return the remaining element

------------------------------------------------------

EXAMPLE:
Input: [2,7,4,1,8,1]

Heap: [8,7,4,2,1,1]

Steps:
8 - 7 = 1 → [4,2,1,1,1]
4 - 2 = 2 → [2,1,1,1]
2 - 1 = 1 → [1,1,1]
1 - 1 = 0 → [1]

Output: 1

------------------------------------------------------

TIME COMPLEXITY:
- Inserting n elements into heap → O(n log n)
- Each removal/insertion → O(log n)
- Total → O(n log n)

SPACE COMPLEXITY:
- O(n) for the heap

------------------------------------------------------

KEY IDEA:
Whenever you need:
- Repeated access to largest/smallest elements
- Remove and reinsert elements

→ Use a HEAP (Priority Queue)
*/

public class LastStoneWeight {

    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int stone : stones) {
            maxHeap.add(stone);
        }

        while (maxHeap.size() > 1) {
            int first = maxHeap.poll();
            int second = maxHeap.poll();

            if (first != second) {
                maxHeap.add(first - second);
            }
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }

    public static void main(String[] args) {
        int[] stones = {2,7,4,1,8,1};
        System.out.println(lastStoneWeight(stones)); // Output: 1
    }
}
