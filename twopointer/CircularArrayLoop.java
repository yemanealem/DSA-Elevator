public class CircularArrayLoop {

    /**
     * Problem: Circular Array Loop
     * -----------------------------
     * Given a circular array of integers nums (positive and negative), determine if there is a cycle.
     * 
     * Conditions for a valid cycle:
     * 1. The cycle must start and end at the same index.
     * 2. The cycle must contain more than 1 element.
     * 3. All numbers in the cycle must be either all positive or all negative (same direction).
     * 
     * Circular movement:
     * next_index = (current_index + nums[current_index]) % n
     * 
     * Examples:
     * Input: nums = [2, -1, 1, 2, 2]
     * Output: true
     * Explanation: There is a cycle: 0 -> 2 -> 3 -> 0
     * 
     * Input: nums = [-1, 2]
     * Output: false
     */

    // Helper function to calculate the next index in circular manner
    private static int nextIndex(int[] nums, int current) {
        int n = nums.length;
        // Use modulo and handle negative numbers
        return ((current + nums[current]) % n + n) % n;
    }

    public static boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        // Step 1: Loop through each index as starting point
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) continue; // Skip if already visited

            int slow = i, fast = i;
            boolean direction = nums[i] > 0; // True = positive, False = negative

            // Step 2: Move slow and fast pointers to detect a cycle
            while (true) {
                // Move slow pointer one step
                int nextSlow = nextIndex(nums, slow);
                if (nums[nextSlow] == 0 || (nums[nextSlow] > 0) != direction) break;

                // Move fast pointer two steps
                int nextFast = nextIndex(nums, fast);
                if (nums[nextFast] == 0 || (nums[nextFast] > 0) != direction) break;
                nextFast = nextIndex(nums, nextFast);
                if (nums[nextFast] == 0 || (nums[nextFast] > 0) != direction) break;

                slow = nextSlow;
                fast = nextFast;

                // Step 3: Check if slow meets fast
                if (slow == fast) {
                    // Check if cycle length > 1 (ignore self-loop)
                    if (slow == nextIndex(nums, slow)) break;
                    return true; // Cycle found
                }
            }

            // Step 4: Mark all elements along this path as 0 to avoid revisiting
            int j = i;
            while (nums[j] != 0 && (nums[j] > 0) == direction) {
                int temp = nextIndex(nums, j);
                nums[j] = 0; // Mark visited
                j = temp;
            }
        }

        // Step 5: No cycle found
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, -1, 1, 2, 2};
        System.out.println(circularArrayLoop(nums1)); // Output: true

        int[] nums2 = {-1, 2};
        System.out.println(circularArrayLoop(nums2)); // Output: false
    }
}
