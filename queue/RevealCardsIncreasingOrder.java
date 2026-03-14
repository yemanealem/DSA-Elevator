import java.util.*;

public class RevealCardsIncreasingOrder {

    /*
    Problem:
    Given a deck of unique integers, reorder the deck so that when we:

    1. Reveal the top card
    2. Move the next top card to the bottom

    The revealed sequence becomes sorted in increasing order.

    Example:
    Input:  [17,13,11,2,3,5,7]
    Output: [2,13,3,11,5,17,7]

    Time Complexity: O(n log n)
    (sorting dominates)

    Space Complexity: O(n)
    (queue + result array)
    */

    public static int[] deckRevealedIncreasing(int[] deck) {

        int n = deck.length;

        // Step 1: Sort cards (because we want increasing reveal)
        Arrays.sort(deck);

        int[] result = new int[n];

        // Step 2: Queue to store indices
        Queue<Integer> queue = new LinkedList<>();

        // Add all positions into queue
        for (int i = 0; i < n; i++) {
            queue.offer(i);
        }

        // Step 3: Simulate placement
        for (int card : deck) {

            // place card in the first available position
            int index = queue.poll();
            result[index] = card;

            // move next index to the back of queue
            if (!queue.isEmpty()) {
                queue.offer(queue.poll());
            }
        }

        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        int[] deck = {17, 13, 11, 2, 3, 5, 7};

        int[] result = deckRevealedIncreasing(deck);

        System.out.println("Final Deck Order:");

        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
