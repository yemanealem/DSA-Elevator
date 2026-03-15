import java.util.*;

/*
------------------------------------------------------------
LeetCode: Single-Threaded CPU

Refactored Version

Approach:
1. Sort tasks by enqueue time.
2. Use Min-Heap ordered by:
      - smallest processing time
      - if tie → smallest original index
3. Simulate CPU time.
4. If no task available → jump time forward.

Time Complexity: O(n log n)
Space Complexity: O(n)
------------------------------------------------------------
*/

public class SingleThreadedCPU {

    public static void main(String[] args) {

        int[][] tasks = {
                {1, 2},
                {2, 4},
                {3, 2},
                {4, 1}
        };

        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.getOrder(tasks)));
    }
}

class Solution {

    public int[] getOrder(int[][] tasks) {

        int n = tasks.length;

        // Create tasks with original index
        int[][] indexedTasks = new int[n][3];

        for (int i = 0; i < n; i++) {
            indexedTasks[i][0] = tasks[i][0]; // enqueue time
            indexedTasks[i][1] = tasks[i][1]; // processing time
            indexedTasks[i][2] = i;           // original index
        }

        // Sort by enqueue time
        Arrays.sort(indexedTasks, Comparator.comparingInt(a -> a[0]));

        // Min-heap: processing time, then index
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[2] - b[2]
        );

        int[] result = new int[n];
        int time = 0;
        int taskIndex = 0;
        int resultIndex = 0;

        while (resultIndex < n) {

            // Add all tasks available at current time
            while (taskIndex < n && indexedTasks[taskIndex][0] <= time) {
                minHeap.offer(indexedTasks[taskIndex]);
                taskIndex++;
            }

            if (minHeap.isEmpty()) {
                // If no tasks available → jump to next enqueue time
                time = indexedTasks[taskIndex][0];
            } else {
                // Process next task
                int[] currentTask = minHeap.poll();
                result[resultIndex++] = currentTask[2];
                time += currentTask[1];
            }
        }

        return result;
    }
}
