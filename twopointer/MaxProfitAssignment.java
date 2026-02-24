import java.util.Arrays;
import java.util.Comparator;

public class MaxProfitAssignment {
     public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {

        int n = difficulty.length;
        int[][] jobs = new int[n][2];

        for (int i = 0; i < n; i++) {
            jobs[i][0] = difficulty[i];
            jobs[i][1] = profit[i];
        }

        // Sort jobs by difficulty
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));

        // Sort workers by ability
        Arrays.sort(worker);

        int i = 0;
        int best = 0;
        int totalProfit = 0;

        for (int w : worker) {
            while (i < n && jobs[i][0] <= w) {
                best = Math.max(best, jobs[i][1]);
                i++;
            }
            totalProfit += best;
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        int[] difficulty = {2, 4, 6, 8, 10};
        int[] profit = {10, 20, 30, 40, 50};
        int[] worker = {4, 5, 6, 7};

        int result = maxProfitAssignment(difficulty, profit, worker);

        System.out.println("Maximum Profit: " + result);
    }
}

