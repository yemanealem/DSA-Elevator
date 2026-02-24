public class MaxProfitAssignment {

    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {

        int max = 0;

        // Find maximum difficulty
        for (int d : difficulty) {
            max = Math.max(max, d);
        }

        // Bucket to store best profit for each difficulty
        int[] best = new int[max + 2];

        // Fill bucket with highest profit per difficulty
        for (int i = 0; i < difficulty.length; i++) {
            best[difficulty[i]] = Math.max(best[difficulty[i]], profit[i]);
        }

        // Prefix maximum (so each level gets best profit up to it)
        for (int i = 1; i <= max; i++) {
            best[i] = Math.max(best[i], best[i - 1]);
        }

        int total = 0;

        // Two-pointer style: for each worker, take best available profit
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
