import java.util.*;

/*
===========================================================
Optimized 4Sum with Pruning

Key Optimizations:
1. Early break if minimum possible sum > target
2. Early continue if maximum possible sum < target
3. Same pruning inside second loop
4. Skip duplicates properly
5. Use long to prevent overflow

Time Complexity: O(n^3)
But MUCH faster in practice due to pruning.
===========================================================
*/

public class FourSum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        if (n < 4) return result;

        Arrays.sort(nums);

        for (int i = 0; i < n - 3; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // 🔥 Early break (smallest possible sum too big)
            long min1 = (long) nums[i] + nums[i+1] + nums[i+2] + nums[i+3];
            if (min1 > target) break;

            // 🔥 Early continue (largest possible sum too small)
            long max1 = (long) nums[i] + nums[n-1] + nums[n-2] + nums[n-3];
            if (max1 < target) continue;

            for (int j = i + 1; j < n - 2; j++) {

                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // 🔥 Inner pruning
                long min2 = (long) nums[i] + nums[j] + nums[j+1] + nums[j+2];
                if (min2 > target) break;

                long max2 = (long) nums[i] + nums[j] + nums[n-1] + nums[n-2];
                if (max2 < target) continue;

                int left = j + 1;
                int right = n - 1;

                while (left < right) {

                    long sum = (long) nums[i] + nums[j] 
                             + nums[left] + nums[right];

                    if (sum == target) {

                        result.add(Arrays.asList(
                                nums[i], nums[j],
                                nums[left], nums[right]
                        ));

                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;

                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[] nums = {1,0,-1,0,-2,2};
        int target = 0;

        List<List<Integer>> result = fourSum(nums, target);

        for (List<Integer> quad : result) {
            System.out.println(quad);
        }
    }
}
