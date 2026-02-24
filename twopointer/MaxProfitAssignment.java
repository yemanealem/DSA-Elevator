public class MaxProfitAssignment {

    // ----------------------------------------------------------------------
    // Question:
    // You are given jobs with difficulty[] and profit[].
    // A worker can do a job only if worker ability >= job difficulty.
    // Each worker can do at most one job.
    // Maximize total profit.
    //
    // Example:
    // difficulty = [2,4,6,8,10]
    // profit     = [10,20,30,40,50]
    // worker     = [4,5,6,7]
    //
    // Output = 100
    // ----------------------------------------------------------------------

    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {

        // ------------------------------------------------------------------
        // How it works (Bucket + Prefix Maximum):
        //
        // 1. Find maximum difficulty (to size the bucket array).
        // 2. Create array best[d] -> stores highest profit for difficulty d.
        // 3. Fill bucket: best[difficulty[i]] = max(best, profit).
        // 4. Prefix max:
        //    best[i] = max(best[i], best[i-1])
        //    -> now best[i] holds best profit for any job <= i difficulty.
        // 5. For each worker:
        //    - If ability > max difficulty -> use best[max]
        //    - Else -> use best[ability]
        //    - Add to total.
        //
        // Trace example:
        // best after prefix:
        // difficulty 0 1 2 3 4 5 ...
        // best       0 0 10 10 20 ...
        // worker=4 -> best[4] = 20 (max job <=4)
        // ------------------------------------------------------------------

        int max = 0;

        // Step 1: find max difficulty
        for (int d : difficulty) {
            max = Math.max(max, d);
        }

        // Step 2: bucket array (best profit for each difficulty)
        int[] best = new int[max + 2];

        // Step 3: fill bucket with highest profit
        for (int i = 0; i < difficulty.length; i++) {
            best[difficulty[i]] = Math.max(best[difficulty[i]], profit[i]);
        }

        // Step 4: prefix maximum (two-pointer style knowledge propagation)
        for (int i = 1; i <= max; i++) {
            best[i] = Math.max(best[i], best[i - 1]);
        }

        int total = 0;

        // Step 5: assign best job to each worker
        for (int w : worker) {
            if (w > max) {
                total += best[max];
            } else {
                total += best[w];
            }
        }

        return total;
    }

    public static void main(String[] args) {
        int[] difficulty = {2, 4, 6, 8, 10};
        int[] profit = {10, 20, 30, 40, 50};
        int[] worker = {4, 5, 6, 7};

        System.out.println(maxProfitAssignment(difficulty, profit, worker));
    }
}
