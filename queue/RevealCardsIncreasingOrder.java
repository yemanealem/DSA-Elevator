import java.util.*;

/*
-------------------------------------------------------------
LeetCode 950: Reveal Cards In Increasing Order
-------------------------------------------------------------

Question:
You are given an integer array deck representing a deck of cards
with unique integers.

You can reorder the deck in any order. Then the following process
is applied repeatedly until all cards are revealed:

1. Reveal the top card of the deck and remove it.
2. If there are still cards left, move the next top card to the bottom.
3. Repeat until all cards are revealed.

Return an ordering of the deck so that the revealed cards appear
in increasing order.

Example:
Input:  deck = [17,13,11,2,3,5,7]
Output: [2,13,3,11,5,17,7]

Because the reveal sequence becomes:
2,3,5,7,11,13,17 (sorted order)

-------------------------------------------------------------
Idea / Strategy
-------------------------------------------------------------

Instead of simulating the reveal process directly, we simulate
where each card should be placed.

Steps:

1. Sort the deck (because we want the reveal order to be increasing).

2. Use a queue to store the indices of the result array:
   queue = [0,1,2,3,4,5,6]

3. For each card in the sorted deck:
   - Take the first index from the queue
   - Place the card in that position
   - Move the next index to the bottom of the queue
     (simulates moving a card to the bottom)

-------------------------------------------------------------
Step-by-Step Example
-------------------------------------------------------------

Sorted deck:
[2,3,5,7,11,13,17]

Initial queue:
[0,1,2,3,4,5,6]

Process:

Card = 2
Place at index 0
Move next index (1) to bottom
Queue -> [2,3,4,5,6,1]

Card = 3
Place at index 2
Move next index (3)
Queue -> [4,5,6,1,3]

Card = 5
Place at index 4
Move next index (5)
Queue -> [6,1,3,5]

Card = 7
Place at index 6
Move next index (1)
Queue -> [3,5,1]

Card = 11
Place at index 3
Move next index (5)
Queue -> [1,5]

Card = 13
Place at index 1
Move next index (5)
Queue -> [5]

Card = 17
Place at index 5

Final result:
[2,13,3,11,5,17,7]

-------------------------------------------------------------
Time Complexity
-------------------------------------------------------------
Sorting: O(n log n)
Queue operations: O(n)

Total: O(n log n)

-------------------------------------------------------------
Space Complexity
-------------------------------------------------------------
O(n)
(result array + queue)
-------------------------------------------------------------
*/

public class RevealCardsIncreasingOrder {

    public static int[] deckRevealedIncreasing(int[] deck) {

        int n = deck.length;

        // Step 1: Sort the cards so we place them in increasing order
        Arrays.sort(deck);

        int[] result = new int[n];

        // Step 2: Use ArrayDeque (faster than LinkedList)
        Deque<Integer> queue = new ArrayDeque<>();

        // Store all indices in queue
        for (int i = 0; i < n; i++) {
            queue.offer(i);
        }

        // Step 3: Simulate placement
        for (int card : deck) {

            // Take the next available position
            int index = queue.poll();

            // Place the current smallest card there
            result[index] = card;

            // Move next index to the bottom (simulate moving card)
            if (!queue.isEmpty()) {
                queue.offer(queue.poll());
            }
        }

        return result;
    }

    // Main method for testing
    public static void main(String[] args) {

        int[] deck = {17, 13, 11, 2, 3, 5, 7};

        int[] result = deckRevealedIncreasing(deck);

        System.out.println("Final Deck Order:");
        System.out.println(Arrays.toString(result));
    }
}
