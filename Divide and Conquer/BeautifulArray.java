class Solution {
    public int[] beautifulArray(int n) {
        List<Integer> result = divide(n);
        return result.stream().mapToInt(i -> i).toArray();
    }

    private List<Integer> divide(int n) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(1);
            return res;
        }

        // Divide
        List<Integer> left = divide((n + 1) / 2); // odds
        List<Integer> right = divide(n / 2);      // evens

        // Conquer
        for (int x : left) {
            res.add(2 * x - 1);
        }
        for (int x : right) {
            res.add(2 * x);
        }

        return res;
    }
}
