public class BestSightseeingPair {

    /**
     * 📌 Problem:
     * Maximize values[i] + values[j] + i - j, where i < j
     *
     * -------------------------------------------------------
     * 🧠 Approach (Kadane-style):
     * Rewrite as:
     * (values[i] + i) + (values[j] - j)
     *
     * - Keep track of best (values[i] + i)
     * - For each j, compute score
     *
     * -------------------------------------------------------
     * ⏱ Time Complexity: O(n)
     * 🧠 Space Complexity: O(1)
     */

    public int maxScoreSightseeingPair(int[] values) {

        int bestLeft = values[0] + 0;   // best (values[i] + i)
        int maxScore = Integer.MIN_VALUE;

        for (int j = 1; j < values.length; j++) {

            // calculate current score
            int currentScore = bestLeft + values[j] - j;

            maxScore = Math.max(maxScore, currentScore);

            // update bestLeft for next iterations
            bestLeft = Math.max(bestLeft, values[j] + j);
        }

        return maxScore;
    }

    public static void main(String[] args) {

        BestSightseeingPair obj = new BestSightseeingPair();

        int[] values = {8, 1, 5, 2, 6};

        System.out.println(obj.maxScoreSightseeingPair(values)); // 11
    }
}
