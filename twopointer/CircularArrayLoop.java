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
     * If the result is negative, wrap it to stay within the array bounds.
     * 
     * Approach (Fast & Slow Pointers / Floyd’s cycle detection):
     * 1. Loop through each index as a starting point.
     * 2. Skip if nums[i] == 0 (already visited or cannot form a loop).
     * 3. Use slow and fast pointers starting at i:
     *      - slow moves one step at a time
     *      - fast moves two steps at a time
     * 4. While moving pointers:
     *      - Ensure all numbers along the path have the same direction (all positive or all negative)
     *      - If slow meets fast, a cycle is detected. Check that cycle length > 1 to ignore self-loops.
     * 5. If no cycle is found, mark all visited elements in this path as 0 to avoid reprocessing.
     * 6. Continue until all indices are checked.
     * 
     * Time Complexity: O(n) since each element is visited at most twice.
     * Space Complexity: O(1) in-place marking.
     * 
     * Examples:
     * Input: nums = [2, -1, 1, 2, 2]
     * Output: true
     * Explanation: There is a cycle: 0 -> 2 -> 3 -> 0
     * 
     * Input: nums = [-1, 2]
     * Output: false
     */
    
    // Helper function to calculate the next index in a circular array
    private static int nextIndex(int[] nums, int current) {
        int n = nums.length;
        return ((current + nums[current]) % n + n) % n; // handle negative wrap
    }

    public static boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) continue;

            int slow = i, fast = i;
            boolean direction = nums[i] > 0;

            while (true) {
                int nextSlow = nextIndex(nums, slow);
                if (nums[nextSlow] == 0 || (nums[nextSlow] > 0) != direction) break;

                int nextFast = nextIndex(nums, fast);
                if (nums[nextFast] == 0 || (nums[nextFast] > 0) != direction) break;
                nextFast = nextIndex(nums, nextFast);
                if (nums[nextFast] == 0 || (nums[nextFast] > 0) != direction) break;

                slow = nextSlow;
                fast = nextFast;

                if (slow == fast) {
                    if (slow == nextIndex(nums, slow)) break; // one-element loop not allowed
                    return true;
                }
            }

            int j = i;
            while (nums[j] != 0 && (nums[j] > 0) == direction) {
                int temp = nextIndex(nums, j);
                nums[j] = 0; // mark visited
                j = temp;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, -1, 1, 2, 2};
        System.out.println(circularArrayLoop(nums1)); // true

        int[] nums2 = {-1, 2};
        System.out.println(circularArrayLoop(nums2)); // false
    }
}
