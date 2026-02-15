/**
 * LeetCode #55 - Jump Game
 * 
 * Problem:
 * Given an array nums where each element represents your maximum jump length at that position,
 * determine if you can reach the last index starting from the first index.
 * 
 * Example:
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * 
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always reach index 3, which has 0 jumps, so you cannot reach the last index.
 * 
 * Approach:
 * - Greedy Algorithm
 * - Keep track of the farthest index we can reach (maxReach).
 * - Iterate through the array:
 *     - If current index i > maxReach, we cannot move forward → return false
 *     - Else, update maxReach = max(maxReach, i + nums[i])
 * - If we finish loop or maxReach >= last index, return true
 * 
 * Why Greedy Works:
 * - Always pick the farthest reachable index at every step.
 * - We don’t need to explore all paths; if there is a way, greedy ensures we can reach it.
 * 
 * Trace Example:
 * nums = [2,3,1,1,4]
 * i=0, nums[0]=2, maxReach=0 -> update maxReach=2
 * i=1, nums[1]=3, maxReach=2 -> update maxReach=4
 * i=2, nums[2]=1, maxReach=4 -> maxReach remains 4
 * i=3, nums[3]=1, maxReach=4 -> maxReach remains 4
 * i=4, nums[4]=4, maxReach=4 -> update maxReach=8 (reached last index)
 * Return true
 */

public class JumpGame {

    // Function to determine if we can reach the last index
    public boolean canJump(int[] nums) {
        int maxReach = 0; // Farthest index we can reach
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            if (i > maxReach) {
                // Current index is unreachable
                return false;
            }
            // Update the farthest reachable index
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        
        return true; // We can reach the end
    }

    // Main method to test the solution
    public static void main(String[] args) {
        JumpGame solution = new JumpGame();

        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Can jump (nums1)? " + solution.canJump(nums1)); // true

        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("Can jump (nums2)? " + solution.canJump(nums2)); // false

        int[] nums3 = {2, 0, 0};
        System.out.println("Can jump (nums3)? " + solution.canJump(nums3)); // true
    }
}
