/*
LeetCode 2073 - Time Needed to Buy Tickets

QUESTION:
You are given an integer array tickets where tickets[i] represents
the number of tickets the i-th person wants to buy.

People are standing in a queue (0-indexed).
Each person can buy exactly one ticket per second.

After buying one ticket:
- If the person still needs tickets, they go to the end of the queue.
- If they are done, they leave the queue.

Given an index k, return the total number of seconds required
for the person at index k to finish buying all their tickets.
*/

public class TimeRequiredToBuy {

    /*
    HOW IT WORKS (OPTIMIZED APPROACH):

    Let target = tickets[k] (number of tickets person k wants).

    We calculate how many times each person gets a chance to buy
    a ticket before person k finishes.

    1. For people at index i <= k:
       They will get to buy tickets in every round including
       the final round when person k buys their last ticket.
       Contribution = min(tickets[i], target)

    2. For people at index i > k:
       They will NOT get a turn in the final round because
       once person k finishes, the process stops.
       Contribution = min(tickets[i], target - 1)

    3. Each ticket takes 1 second, so summing all contributions
       gives the total time in seconds.

    Time Complexity: O(n)
    Space Complexity: O(1)
    */
    public static int timeRequiredToBuy(int[] tickets, int k) {
        int totalTime = 0;
        int target = tickets[k];

        for (int i = 0; i < tickets.length; i++) {
            if (i <= k) {
                totalTime += Math.min(tickets[i], target);
            } else {
                totalTime += Math.min(tickets[i], target - 1);
            }
        }
        return totalTime;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        int[] tickets = {2, 3, 2};
        int k = 2;

        int result = timeRequiredToBuy(tickets, k);

        System.out.println("Time needed to buy tickets: " + result);
    }
}
