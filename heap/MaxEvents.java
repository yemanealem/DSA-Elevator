import java.util.*;

/*
LeetCode: Maximum Number of Events That Can Be Attended

QUESTION:
You are given an array of events where:
events[i] = [startDay, endDay]

You can attend only ONE event per day.
Each event can be attended on any day between startDay and endDay (inclusive).

Return the maximum number of events you can attend.

------------------------------------------------------

OPTIMAL APPROACH (GREEDY + MIN HEAP):

KEY IDEA:
- Always attend the event that ENDS EARLIEST
- Why? → This leaves more room for future events

------------------------------------------------------

HOW IT WORKS:

1. Sort events by start day
2. Use a Min Heap to store end days
3. Iterate day by day:
   - Add all events starting today to heap
   - Remove events that already expired (end < current day)
   - Attend the event with smallest end (poll from heap)

------------------------------------------------------

EXAMPLE:

events = [[1,2],[2,3],[3,4]]

Day 1:
 add [1,2] → attend → heap empty

Day 2:
 add [2,3] → attend → heap empty

Day 3:
 add [3,4] → attend → heap empty

Result = 3

------------------------------------------------------

TIME COMPLEXITY:
- Sorting → O(n log n)
- Heap operations → O(n log n)
- Total → O(n log n)

SPACE COMPLEXITY:
- O(n) for heap

------------------------------------------------------

WHY THIS IS OPTIMAL:
- Greedy choice: attend earliest finishing event
- Ensures maximum room for future scheduling
*/

public class MaxEvents {

    public static int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]); // sort by start day

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // store end days

        int day = 0;
        int i = 0;
        int n = events.length;
        int attended = 0;

        while (i < n || !minHeap.isEmpty()) {

            // jump to next available day
            if (minHeap.isEmpty()) {
                day = events[i][0];
            }

            // add all events starting today
            while (i < n && events[i][0] <= day) {
                minHeap.add(events[i][1]);
                i++;
            }

            // remove expired events
            while (!minHeap.isEmpty() && minHeap.peek() < day) {
                minHeap.poll();
            }

            // attend one event
            if (!minHeap.isEmpty()) {
                minHeap.poll();
                attended++;
                day++;
            }
        }

        return attended;
    }

    public static void main(String[] args) {
        int[][] events = {{1,2},{2,3},{3,4}};
        System.out.println(maxEvents(events)); // Output: 3
    }
}
