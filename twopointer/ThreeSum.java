import java.util.*;

public class ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        if (n < 3) return result;

        Arrays.sort(nums);

        for (int i = 0; i < n - 2; i++) {

            // 🔥 Early termination: if the smallest number is > 0
            if (nums[i] > 0) break;

            // Skip duplicate i
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = n - 1;
            int target = -nums[i]; // Avoid repeated addition

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    int leftVal = nums[left];
                    int rightVal = nums[right];

                    // Skip duplicates faster
                    while (left < right && nums[left] == leftVal) left++;
                    while (left < right && nums[right] == rightVal) right--;

                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }
}
