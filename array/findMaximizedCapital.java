import java.util.*;

class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        int n = profits.length;

        // Pair: (capital, profit)
        int[][] projects = new int[n][2];

        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }

        // Sort by capital
        Arrays.sort(projects, (a, b) -> a[0] - b[0]);

        // Max heap for profit
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int i = 0;

        for (int j = 0; j < k; j++) {

            // add all affordable projects
            while (i < n && projects[i][0] <= w) {
                pq.add(projects[i][1]);
                i++;
            }

            // if no project available
            if (pq.isEmpty()) break;

            // pick best profit
            w += pq.poll();
        }

        return w;
    }
}
