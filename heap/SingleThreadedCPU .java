import java.util.*;

/*
------------------------------------------------------------
LeetCode: Single-Threaded CPU

Optimized Strategy:
1. Sort tasks by enqueue time.
2. Use Min-Heap (processing time, index).
3. Simulate time.
4. Jump time if heap empty.

Improvement:
- Cleaner logic
- Less object overhead
- Faster comparator
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
        System.out.println(Arrays.toString(solution.getOrder(tasks)));
    }
}

class Solution {

    public int[] getOrder(int[][] tasks) {

        int n = tasks.length;

        // Attach index
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            arr[i][0] = tasks[i][0];
            arr[i][1] = tasks[i][1];
            arr[i][2] = i;
        }

        // Sort by enqueue time
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        // Min Heap: [processingTime, index]
        PriorityQueue<int[]> heap = new PriorityQueue<>(
                (a, b) -> {
                    if (a[0] != b[0])
                        return a[0] - b[0];
                    return a[1] - b[1];
                }
        );

        int[] result = new int[n];

        long time = 0;   // use long to avoid overflow
        int i = 0;
        int idx = 0;

        while (idx < n) {

            // Add available tasks
            while (i < n && arr[i][0] <= time) {
                heap.offer(new int[]{arr[i][1], arr[i][2]});
                i++;
            }

            if (heap.isEmpty()) {
                time = arr[i][0];
            } else {
                int[] task = heap.poll();
                result[idx++] = task[1];
                time += task[0];
            }
        }

        return result;
    }
}
