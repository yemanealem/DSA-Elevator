import java.util.Arrays;

public class ThreeSumClosest {

    /**
     * Problem: 3Sum Closest
     * ----------------------
     * Given an integer array nums and an integer target,
     * find three integers in nums such that the sum is closest to target.
     * Return the sum of the three integers.
     *
     * Example:
     * Input: nums = [-1, 2, 1, -4], target = 1
     * Output: 2
     * Explanation: The sum that is closest to the target is 2 (-1 + 2 + 1 = 2).
     *
     * Approach:
     * 1. Sort the array to use the two-pointer technique.
     * 2. Initialize closestSum with the sum of the first three elements.
     * 3. Loop through each element as the first element of the triplet.
     * 4. Use two pointers (left and right) to find the other two elements.
     * 5. Update closestSum if current sum is closer to target.
     * 6. Move pointers based on whether current sum is less than or greater than target.
     * 7. Return closestSum after checking all possibilities.
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // Step 1: Sort the array
        int closestSum = nums[0] + nums[1] + nums[2]; // Step 2: Initialize closestSum

        // Step 3: Loop through each number as the first element of the triplet
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1; // Pointer to the next element
            int right = nums.length - 1; // Pointer to the last element

            // Step 4: Move the two pointers to find the closest sum
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];

                // Step 5: Update closestSum if currentSum is closer to target
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }

                // Step 6: Move pointers intelligently
                if (currentSum < target) {
                    left++; // Increase sum
                } else if (currentSum > target) {
                    right--; // Decrease sum
                } else {
                    return target; // Exact match found
                }
            }
        }

        // Step 7: Return the closest sum found
        return closestSum;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;

        // Call the method and print result
        System.out.println("Closest sum: " + threeSumClosest(nums, target)); // Output: 2
    }
}
