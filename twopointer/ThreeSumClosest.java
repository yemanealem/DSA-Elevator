import java.util.Arrays;

public class ThreeSumClosest {

    public static int threeSumClosest(int[] nums, int target) {
        // Step 1: Sort the array
        Arrays.sort(nums);
        int n = nums.length;

        // Step 2: Initialize closestSum with the sum of first three numbers
        int closestSum = nums[0] + nums[1] + nums[2];

        // Step 3: Loop through each number as the first element of triplet
        for (int i = 0; i < n - 2; i++) {

            int left = i + 1;      // Pointer to the next element
            int right = n - 1;     // Pointer to the last element

            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];

                // Update closestSum if currentSum is closer to target
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }

                // Move pointers intelligently
                if (currentSum < target) {
                    left++;  // Increase sum
                } else if (currentSum > target) {
                    right--; // Decrease sum
                } else {
                    // Exact match found
                    return target;
                }
            }
        }

        return closestSum;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        System.out.println("Closest sum: " + threeSumClosest(nums, target)); // Output: 2
    }
}
