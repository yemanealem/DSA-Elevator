import java.util.*;

/*
------------------------------------------------------------
LeetCode: Single-Threaded CPU (Medium)

Approach:
1. Sort tasks by enqueue time.
2. Use Min-Heap to select task with:
      - smallest processing time
      - if tie → smallest index
3. Simulate CPU time.
4. If no tasks available → jump time forward.

Time Complexity: O(n log n)
Space Complexity: O(n)
------------------------------------------------------------
*/

public class SingleThreadedCPU {

    public static void main(String[] args) {

        int[][] tasks = {
                {1,2},
                {2,4},
                {3,2},
                {4,1}
        };

        Solution solution = new Solution();
        int[] result = solution.getOrder(tasks);

        System.out.println(Arrays.toString(result));
    }
}

class Solution {

    public int[] getOrder(int[][] tasks) {

        int n = tasks.length;

        // Add original index
        int[][] indexedTasks = new int[n][3];

        for (int i = 0; i < n; i++) {
            indexedTasks[i][0] = tasks[i][0]; // enqueue time
            indexedTasks[i][1] = tasks[i][1]; // processing time
            indexedTasks[i][2] = i;           // original index
        }

        // Sort by enqueue time
        Arrays.sort(indexedTasks, (a, b) -> a[0] - b[0]);

        // Min-heap: first processing time, then index
        PriorityQueue<int[]> minHeap =
                new PriorityQueue<>((a, b) -> {
                    if (a[1] == b[1])
                        return a[2] - b[2];
                    return a[1] - b[1];
                });

        int[] result = new int[n];
        int time = 0, i = 0, index = 0;

        while (i < n || !minHeap.isEmpty()) {

            // Add all available tasks
            while (i < n && indexedTasks[i][0] <= time) {
                minHeap.offer(indexedTasks[i]);
                i++;
            }

            if (minHeap.isEmpty()) {
                // If no task available → jump time
                time = indexedTasks[i][0];
            } else {
                int[] task = minHeap.poll();
                result[index++] = task[2];
                time += task[1];
            }
        }

        return result;
    }
}
