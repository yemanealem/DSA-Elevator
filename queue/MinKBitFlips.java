class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;

        int[] diff = new int[n + 1]; // flip markers
        int flip = 0;                // current parity
        int res = 0;

        for (int i = 0; i < n; i++) {

            // remove expired flip effect
            flip ^= diff[i];

            // if current bit after flips is 0 → must flip
            if (nums[i] == flip) {

                if (i + k > n) return -1;

                res++;

                flip ^= 1;        // apply flip starting here
                diff[i + k] ^= 1; // cancel flip after k steps
            }
        }

        return res;
    }
}
