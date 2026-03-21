import java.util.Stack;

public class Pattern132 {
    public static boolean find132pattern(int[] nums) {
        if (nums.length < 3) return false;

        Stack<Integer> stack = new Stack<>();
        int third = Integer.MIN_VALUE; // potential "2" in 132

        // Traverse from right to left
        for (int i = nums.length - 1; i >= 0; i--) {
            // If current number < third, 132 pattern found
            if (nums[i] < third) {
                return true;
            }
            // nums[i] could be the "3", pop stack to update "2"
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                third = stack.pop();
            }
            // Push current number as potential "3"
            stack.push(nums[i]);
        }

        return false; // no pattern found
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 1, 4, 2};
        int[] nums2 = {1, 2, 3, 4};
        int[] nums3 = {1, 4, 0, -1, -2, -3, -1, -2};

        System.out.println(find132pattern(nums1)); // true
        System.out.println(find132pattern(nums2)); // false
        System.out.println(find132pattern(nums3)); // true
    }
}

/*
Trace Example: nums = [3, 1, 4, 2]

Start from right:
i=3, nums[i]=2 → stack empty → push 2, third=MIN
i=2, nums[i]=4 → nums[i]>stack.peek() → pop 2, third=2 → push 4
i=1, nums[i]=1 → nums[i]<third (1<2) → return true

Time Complexity: O(n)
Space Complexity: O(n)
*/
