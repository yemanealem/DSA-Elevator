/*
LeetCode: Maximum Gap

Question:
Find the maximum difference between successive elements in sorted form.

Idea (Bucket Sort / Pigeonhole Principle):
1. If n elements, the maximum gap is at least:
   gap >= (max - min) / (n - 1)
2. Create buckets to store:
   - min value in bucket
   - max value in bucket
3. Only compute gap between buckets (not inside)

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class MaximumGap {

    public static int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        int n = nums.length;

        int min = nums[0], max = nums[0];
        for (int num : nums) {
            if (num < min) min = num;
            if (num > max) max = num;
        }

        if (min == max) return 0;

        int bucketSize = Math.max(1, (max - min) / (n - 1));
        int bucketCount = (max - min) / bucketSize + 1;

        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        boolean[] used = new boolean[bucketCount];

        for (int num : nums) {
            int idx = (num - min) / bucketSize;

            if (!used[idx]) {
                bucketMin[idx] = num;
                bucketMax[idx] = num;
                used[idx] = true;
            } else {
                bucketMin[idx] = Math.min(bucketMin[idx], num);
                bucketMax[idx] = Math.max(bucketMax[idx], num);
            }
        }

        int maxGap = 0;
        int prevMax = min;

        for (int i = 0; i < bucketCount; i++) {
            if (!used[i]) continue;

            maxGap = Math.max(maxGap, bucketMin[i] - prevMax);
            prevMax = bucketMax[i];
        }

        return maxGap;
    }

    public static void main(String[] args) {
        int[] nums = {3, 6, 9, 1};
        System.out.println(maximumGap(nums));
    }
}
