import java.util.*;

public class IPO {

    /*
     * LeetCode 502 - IPO
     * 
     * Problem:
     * You are given k projects, initial capital w, and arrays:
     * - profits[i] = profit of project i
     * - capital[i] = minimum capital required to start project i
     *
     * You can complete at most k projects. Each time, you can only pick
     * projects you can afford (capital <= current w), and you want to
     * maximize your final capital.
     *
     * How it works:
     * 1. Sort projects by required capital.
     * 2. Use a max heap to store profits of all affordable projects.
     * 3. For each of k steps:
     *    - Add all projects we can afford into heap
     *    - Pick the project with maximum profit
     *    - Add it to current capital
     *
     * Running Time:
     * O(n log n) where n = number of projects
     */

    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        int n = profits.length;
        int[][] projects = new int[n][2];

        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }

        Arrays.sort(projects, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int i = 0;

        for (int j = 0; j < k; j++) {

            while (i < n && projects[i][0] <= w) {
                pq.add(projects[i][1]);
                i++;
            }

            if (pq.isEmpty()) break;

            w += pq.poll();
        }

        return w;
    }

    public static void main(String[] args) {

        int k = 2;
        int w = 0;
        int[] profits = {1, 2, 3};
        int[] capital = {0, 1, 1};

        int result = findMaximizedCapital(k, w, profits, capital);

        System.out.println("Maximized Capital: " + result);
    }
}
