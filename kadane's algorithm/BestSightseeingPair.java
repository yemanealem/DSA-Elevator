public class BestSightseeingPair {

    /**
     * 📌 Problem: Best Sightseeing Pair
     *
     * Maximize:
     * values[i] + values[j] + i - j
     * where i < j
     *
     * -------------------------------------------------------
     * 🧠 Kadane-Style Idea:
     *
     * Rewrite as:
     * (values[i] + i) + (values[j] - j)
     *
     * Keep track of maximum (values[i] + i)
     * while iterating j.
     *
     * -------------------------------------------------------
     * ⏱️ Time Complexity: O(n)
     * 🧠 Space Complexity: O(1)
     */
    public int maxScoreSightseeingPair(int[] values) {

        int bestLeft = values[0] + 0; // max(values[i] + i)
        int maxScore = Integer.MIN_VALUE;

        for (int j = 1; j < values.length; j++) {

            // compute current score
            int current = bestLeft + values[j] - j;

            maxScore = Math.max(maxScore, current);

            // update bestLeft (Kadane-like running max)
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
