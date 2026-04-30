public class MinimumKBitFlips {

    public static int minKBitFlips(int[] nums, int k) {
        int n = nums.length;

        int[] diff = new int[n + 1];
        int flip = 0;
        int res = 0;

        for (int i = 0; i < n; i++) {

            // remove expired flip effect
            flip ^= diff[i];

            // if current bit after flips is 0 → we must flip here
            if (nums[i] == flip) {

                if (i + k > n) {
                    return -1;
                }

                res++;

                flip ^= 1;         // start new flip
                diff[i + k] ^= 1;  // mark when flip ends
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0};
        int k = 1;

        int result = minKBitFlips(nums, k);

        System.out.println("Minimum flips = " + result);
    }
}
