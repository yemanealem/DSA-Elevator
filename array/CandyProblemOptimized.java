public class CandyProblemOptimized {
    public static int candy(int[] ratings) {
        int n = ratings.length;
        if (n == 0) return 0;

        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = 1;
        }

        // Left to right
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }

        int total = left[n - 1];
        int right = 1;

        // Right to left
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            total += Math.max(left[i], right) - left[i];
        }

        return total;
    }

    public static void main(String[] args) {
        int[] ratings1 = {1, 0, 2};
        int[] ratings2 = {1, 2, 3, 2, 1};
        System.out.println(candy(ratings1)); // 5
        System.out.println(candy(ratings2)); // 9
    }
}
