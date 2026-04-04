import java.util.*;

/*
LeetCode: Maximum Number of Events That Can Be Attended

OPTIMIZED GREEDY + MIN HEAP

KEY IMPROVEMENT:
- Avoid iterating through every day unnecessarily
- Jump directly to next event start when heap is empty

------------------------------------------------------

HOW IT WORKS:

1. Sort events by start day
2. Use a Min Heap (store end days)
3. Process events efficiently:
   - If heap is empty → jump to next event start
   - Add all available events
   - Remove expired ones
   - Attend one event (earliest ending)

------------------------------------------------------

TIME COMPLEXITY:
- O(n log n)

SPACE:
- O(n)

------------------------------------------------------

WHY THIS IS FAST:
- No unnecessary day iteration
- Heap operations minimized
*/

public class MaxEvents {

    public static int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int i = 0;
        int n = events.length;
        int day = 0;
        int count = 0;

        while (i < n || !minHeap.isEmpty()) {

            // Jump to next available event day
            if (minHeap.isEmpty()) {
                day = events[i][0];
            }

            // Add all events starting up to current day
            while (i < n && events[i][0] <= day) {
                minHeap.offer(events[i][1]);
                i++;
            }

            // Remove expired events
            while (!minHeap.isEmpty() && minHeap.peek() < day) {
                minHeap.poll();
            }

            // Attend one event
            if (!minHeap.isEmpty()) {
                minHeap.poll();
                count++;
                day++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] events = {{1,2},{2,3},{3,4}};
        System.out.println(maxEvents(events)); // Output: 3
    }
}
