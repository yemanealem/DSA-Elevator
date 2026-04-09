class Solution {
    public int[] beautifulArray(int n) {
        int[] res = new int[n];
        res[0] = 1;

        int size = 1;

        while (size < n) {
            int[] temp = new int[n];
            int idx = 0;

            // odds
            for (int i = 0; i < size; i++) {
                int val = 2 * res[i] - 1;
                if (val <= n) temp[idx++] = val;
            }

            // evens
            for (int i = 0; i < size; i++) {
                int val = 2 * res[i];
                if (val <= n) temp[idx++] = val;
            }

            res = temp;
            size = idx;
        }

        return res;
    }
}
