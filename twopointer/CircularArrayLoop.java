public class CircularArrayLoop {
    
    // Helper function to get the next index (circular)
    private static int nextIndex(int[] nums, int current) {
        int n = nums.length;
        return ((current + nums[current]) % n + n) % n; // handle negative wrap
    }

    public static boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) continue; // already visited

            int slow = i, fast = i;
            boolean direction = nums[i] > 0; // true = positive, false = negative

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

                if (slow == fast) {
                    if (slow == nextIndex(nums, slow)) break; // one element cycle not allowed
                    return true;
                }
            }

            // Mark all elements in this path as 0 to avoid revisiting
            int j = i;
            while (nums[j] != 0 && (nums[j] > 0) == direction) {
                int temp = nextIndex(nums, j);
                nums[j] = 0;
                j = temp;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2, -1, 1, 2, 2};
        System.out.println(circularArrayLoop(nums)); // Output: true

        int[] nums2 = {-1, 2};
        System.out.println(circularArrayLoop(nums2)); // Output: false
    }
}
