import java.util.*;

/*
    LeetCode: Largest Divisible Subset

    How it works:
    1. Sort the array so divisibility can be checked in one direction.
    2. Use DP where dp[i] is the size of the largest divisible subset ending at i.
    3. Use parent array to reconstruct the subset.
    4. For each i, check all j < i:
        if nums[i] % nums[j] == 0 → update dp[i].

    Time Complexity:
    O(N^2)
    - Two nested loops over array
*/

public class LargestDivisibleSubset {

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();

        Arrays.sort(nums);

        int n = nums.length;
        int[] dp = new int[n];
        int[] parent = new int[n];

        Arrays.fill(dp, 1);
        Arrays.fill(parent, -1);

        int maxSize = 1;
        int maxIndex = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }

            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxIndex = i;
            }
        }

        List<Integer> result = new ArrayList<>();

        while (maxIndex != -1) {
            result.add(nums[maxIndex]);
            maxIndex = parent[maxIndex];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 8, 9, 72};

        System.out.println(largestDivisibleSubset(nums));
    }
}