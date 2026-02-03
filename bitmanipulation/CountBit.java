class Solution {
    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        bits[0] = 0; // 0 has 0 bits

        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i & (i - 1)] + 1; // remove lowest set bit +1
        }

        return bits;
    }
}