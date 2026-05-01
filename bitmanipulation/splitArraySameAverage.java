class Solution {
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        int sum = 0;

        for (int x : nums) sum += x;

        for (int mask = 1; mask < (1 << n) - 1; mask++) {

            int count = 0;
            int subsetSum = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    count++;
                    subsetSum += nums[i];
                }
            }

            if (count > 0 && count < n) {
                if (subsetSum * n == sum * count) {
                    return true;
                }
            }
        }

        return false;
    }
}
