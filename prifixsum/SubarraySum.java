import java.util.HashMap;

public class SubarraySum {

    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, 1); // Base case: prefix sum 0 exists once

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;

            // If prefixSum - k exists, add its frequency
            if (map.containsKey(prefixSum - k)) {
                count += map.get(prefixSum - k);
            }

            // Store/update prefix sum frequency
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        SubarraySum solution = new SubarraySum();

        int[] nums = {1, 1, 1};
        System.out.println(solution.subarraySum(nums, 2)); // Output: 2

        int[] nums2 = {1, 2, 3};
        System.out.println(solution.subarraySum(nums2, 3)); // Output: 2
    }
}
