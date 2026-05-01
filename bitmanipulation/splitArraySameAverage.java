/*
Problem: Split Array With Same Average (LeetCode 805)

Given an integer array, determine if it can be split into two non-empty subsets
such that both subsets have the same average.

Key Insight:
We transform the condition:

    sum(A) / len(A) = totalSum / n

Into:

    sum(A) = len(A) * totalSum / n

So we only need to find a subset of size k that satisfies this equation.

Approach:
- Use Meet-in-the-Middle (split array into two halves)
- Generate all subset sums grouped by subset size
- Check if any valid combination exists

Time Complexity: O(2^(n/2))
Space Complexity: O(2^(n/2))
*/

import java.util.*;

public class SplitArraySameAverage {

    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        int sum = 0;

        for (int x : nums) sum += x;

        Arrays.sort(nums);

        int mid = n / 2;

        Map<Integer, Set<Integer>> left = new HashMap<>();
        Map<Integer, Set<Integer>> right = new HashMap<>();

        generate(nums, 0, mid, 0, 0, left);
        generate(nums, mid, n, 0, 0, right);

        for (int k = 1; k < n; k++) {

            if ((sum * k) % n != 0) continue;

            int target = (sum * k) / n;

            for (int i = 0; i <= k; i++) {

                if (!left.containsKey(i) || !right.containsKey(k - i)) continue;

                for (int s1 : left.get(i)) {
                    int need = target - s1;

                    if (right.get(k - i).contains(need)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private void generate(int[] nums, int start, int end,
                          int count, int sum,
                          Map<Integer, Set<Integer>> map) {

        if (start == end) {
            map.putIfAbsent(count, new HashSet<>());
            map.get(count).add(sum);
            return;
        }

        generate(nums, start + 1, end, count, sum, map);
        generate(nums, start + 1, end, count + 1, sum + nums[start], map);
    }

    public static void main(String[] args) {
        SplitArraySameAverage sol = new SplitArraySameAverage();

        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};

        System.out.println(sol.splitArraySameAverage(nums));
    }
}
