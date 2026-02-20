 int n = ratings.length;
        if (n == 0) return 0;

        // Left-to-right array to track increasing sequence
        int[] left = new int[n];
        for (int i = 0; i < n; i++) left[i] = 1;

        // Left to right
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }

        int total = 0;
        int right = 1;

        // Right to left
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else if (i < n - 1 && ratings[i] <= ratings[i + 1]) {
                right = 1;
            }
            total += Math.max(left[i], right);
        }

        return total;