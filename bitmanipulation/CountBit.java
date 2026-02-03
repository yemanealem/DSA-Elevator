import java.util.Arrays;

class Solution {
    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 10;
        int[] result = sol.countBits(n);
        System.out.println(Arrays.toString(result)); 
        // Output: [0,1,1,2,1,2,2,3,1,2,2]
    }
}
