public class TotalFruit {

    // Optimized sliding window solution
    public int totalFruit(int[] fruits) {
        int ans = 0;
        int lastFruit = -1, secondLastFruit = -1;
        int lastFruitCount = 0; // consecutive count of lastFruit
        int currentMax = 0;

        for (int fruit : fruits) {
            if (fruit == lastFruit || fruit == secondLastFruit) {
                // extend the window
                currentMax++;
            } else {
                // reset window: last contiguous segment + new fruit
                currentMax = lastFruitCount + 1;
            }

            // update lastFruitCount and track last/secondLast fruit
            if (fruit == lastFruit) {
                lastFruitCount++;
            } else {
                lastFruitCount = 1;
                secondLastFruit = lastFruit;
                lastFruit = fruit;
            }

            ans = Math.max(ans, currentMax);
        }

        return ans;
    }

    // Main method for testing
    public static void main(String[] args) {
        TotalFruit solution = new TotalFruit();

        int[] fruits1 = {1, 2, 1};
        System.out.println("Max fruits collected: " + solution.totalFruit(fruits1)); // 3

        int[] fruits2 = {0, 1, 2, 2};
        System.out.println("Max fruits collected: " + solution.totalFruit(fruits2)); // 3

        int[] fruits3 = {1, 2, 3, 2, 2};
        System.out.println("Max fruits collected: " + solution.totalFruit(fruits3)); // 4
    }
}
