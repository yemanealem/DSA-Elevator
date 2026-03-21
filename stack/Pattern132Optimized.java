/*
LeetCode 456: 132 Pattern

Problem:
- Given an array of integers nums, return true if there exists a 132 pattern in the array.
- A 132 pattern is a subsequence of three integers nums[i], nums[j], nums[k] such that:
      i < j < k and nums[i] < nums[k] < nums[j]

Example:
Input:  nums = [3, 1, 4, 2]
Output: true
Explanation: The sequence [1, 4, 2] is a 132 pattern.

Constraints:
1 <= nums.length <= 10^4
-10^9 <= nums[i] <= 10^9

Approach (Stack-based O(n) solution):
1. Traverse the array from right to left to efficiently find potential "3" and "2" in the 132 pattern.
2. Use a stack to store potential "3" values (nums[j]) in decreasing order.
3. Maintain a variable "third" to track the maximum valid "2" (nums[k]) seen so far.
4. For each number nums[i]:
    a) If nums[i] < third → 132 pattern found → return true
    b) While nums[i] > stack[top] → pop stack and update third = stack[top] (new potential "2")
    c) Push nums[i] onto stack as a potential "3"
5. If traversal completes without returning → no 132 pattern → return false

Time Complexity: O(n) amortized (each element pushed/popped once)
Space Complexity: O(n) for the stack

Trace Example: nums = [3, 1, 4, 2]
- i=3, nums[i]=2 → stack empty → push 2, third=MIN
- i=2, nums[i]=4 → nums[i]>stack[top]=2 → pop 2 → third=2 → push 4
- i=1, nums[i]=1 → nums[i]<third → 1<2 → return true
*/

public class Pattern132Optimized {

    public static boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;

        // Primitive array as stack for speed
        int[] stack = new int[n];
        int top = -1; // stack pointer
        int third = Integer.MIN_VALUE; // potential "2" in 132

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < third) return true; // found 132 pattern

            while (top >= 0 && nums[i] > stack[top]) {
                third = stack[top--]; // update "2"
            }

            stack[++top] = nums[i]; // push current as potential "3"
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
