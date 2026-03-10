class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;         // left pointer of window
        int maxLength = 0;    // result
        int zeroCount = 0;    // number of zeros in window

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            // Shrink window if zeros exceed k
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // Update max length of window
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
