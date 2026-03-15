import java.util.*;

/*
------------------------------------------------------------
LeetCode: Furthest Building You Can Reach (Medium)

You are given:
- heights[]
- bricks
- ladders

You can move to next building if:
- No height increase → free
- Height increases → use bricks or ladders

Goal:
Reach the furthest building index possible.

Approach:
Use Min-Heap (PriorityQueue).

Idea:
- Store all positive height differences in heap.
- When number of climbs exceeds ladders,
  use bricks for the smallest climb.
- If bricks < 0 → stop.

Time Complexity: O(n log n)
Space Complexity: O(n)
------------------------------------------------------------
*/

public class FurthestBuilding {

    public static void main(String[] args) {

        int[] heights = {4,2,7,6,9,14,12};
        int bricks = 5;
        int ladders = 1;

        Solution solution = new Solution();
        int result = solution.furthestBuilding(heights, bricks, ladders);

        System.out.println("Furthest building index: " + result);
    }
}

class Solution {

    public int furthestBuilding(int[] heights, int bricks, int ladders) {

        // Min-heap to store climbs
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < heights.length - 1; i++) {

            int diff = heights[i + 1] - heights[i];

            // Only consider upward climbs
            if (diff > 0) {
                minHeap.add(diff);

                // If we used more climbs than ladders,
                // replace smallest climb with bricks
                if (minHeap.size() > ladders) {
                    bricks -= minHeap.poll();
                }

                // If bricks run out → cannot continue
                if (bricks < 0) {
                    return i;
                }
            }
        }

        return heights.length - 1;
    }
}
