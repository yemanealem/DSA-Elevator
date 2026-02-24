public class MaxProfitAssignment {
    int n = difficulty.length;

        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;

        // Sort indices by difficulty (avoids building 2D array)
        Arrays.sort(idx, (a, b) -> Integer.compare(difficulty[a], difficulty[b]));

        Arrays.sort(worker);

        int i = 0;
        int best = 0;
        int total = 0;

        for (int w : worker) {
            while (i < n && difficulty[idx[i]] <= w) {
                best = Math.max(best, profit[idx[i]]);
                i++;
            }
            total += best;
        }

        return total;
}
