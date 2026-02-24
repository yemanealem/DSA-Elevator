public class MaxProfitAssignment {
     int n = difficulty.length;
        int[][] jobs = new int[n][2];

        for (int i = 0; i < n; i++) {
            jobs[i][0] = difficulty[i];
            jobs[i][1] = profit[i];
        }

        Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));

        Arrays.sort(worker);

        int i = 0; 
        int best = 0;
        int total = 0;

        for (int w : worker) {

            while (i < n && jobs[i][0] <= w) {
                best = Math.max(best, jobs[i][1]);
                i++;
            }

            total += best;
        }

        return total;
}
