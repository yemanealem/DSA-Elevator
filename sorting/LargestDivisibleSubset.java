import java.util.*;

/*
    LeetCode: Largest Divisible Subset (Optimized)

    Idea:
    - Sort array
    - DP[i] = largest subset ending at i
    - prev[i] = previous index in chain
    - Reconstruct from max index

    Optimization:
    - Reduce repeated array access
    - Use local variables inside loops
    - Keep logic tight for JVM optimization

    Time Complexity: O(N^2)
    Space Complexity: O(N)
*/

public class LargestDivisibleSubset {

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        if (n == 0) return new ArrayList<>();

        Arrays.sort(nums);

        int[] dp = new int[n];
        int[] prev = new int[n];

        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        int maxLen = 1;
        int maxIdx = 0;

        for (int i = 0; i < n; i++) {
            int curr = nums[i]; // local cache

            for (int j = 0; j < i; j++) {
                int prevVal = nums[j];

                if (curr % prevVal == 0) {
                    int candidate = dp[j] + 1;

                    if (candidate > dp[i]) {
                        dp[i] = candidate;
                        prev[i] = j;
                    }
                }
            }

            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIdx = i;
            }
        }

        List<Integer> result = new ArrayList<>();

        while (maxIdx != -1) {
            result.add(nums[maxIdx]);
            maxIdx = prev[maxIdx];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 8, 9, 72};

        System.out.println(largestDivisibleSubset(nums));
    }
}