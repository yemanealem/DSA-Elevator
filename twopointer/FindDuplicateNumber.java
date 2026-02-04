public class FindDuplicateNumber {

    public int findDuplicate(int[] nums) {
        // Phase 1: Find intersection point in the cycle
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];         // move one step
            fast = nums[nums[fast]];   // move two steps
        } while (slow != fast);

        // Phase 2: Find entrance to the cycle (duplicate number)
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow; // duplicate number
    }

    // Optional main method to test
    public static void main(String[] args) {
        FindDuplicateNumber fd = new FindDuplicateNumber();
        int[] nums1 = {1,3,4,2,2};
        int[] nums2 = {3,1,3,4,2};
        System.out.println(fd.findDuplicate(nums1)); // Output: 2
        System.out.println(fd.findDuplicate(nums2)); // Output: 3
    }
}
