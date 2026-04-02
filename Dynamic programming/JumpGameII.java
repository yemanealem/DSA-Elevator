public class JumpGameII {

    /*
     * LeetCode: Jump Game II
     *
     * QUESTION:
     * You are given an integer array nums where each element represents
     * the maximum jump length at that position.
     *
     * Starting from index 0, return the minimum number of jumps
     * required to reach the last index.
     *
     * It is guaranteed that you can always reach the last index.
     *
     * ------------------------------------------------------------
     * EXAMPLE:
     * Input: nums = [2,3,1,1,4]
     * Output: 2
     *
     * Explanation:
     * Jump 1 step from index 0 → index 1
     * Jump 3 steps from index 1 → last index
     *
     * ------------------------------------------------------------
     * HOW IT WORKS (Greedy Approach - Optimal):
     *
     * Instead of DP, we use a greedy BFS-like approach.
     *
     * We track 3 variables:
     *
     * 1. jumps → number of jumps made
     * 2. currentEnd → end of current jump range
     * 3. farthest → farthest index reachable so far
     *
     * Logic:
     * - Iterate through the array (except last index)
     * - At each index:
     *      Update farthest = max(farthest, i + nums[i])
     *
     * - If we reach currentEnd:
     *      → we must make a jump
     *      → increment jumps
     *      → update currentEnd = farthest
     *
     * This works like expanding levels in BFS:
     * Each jump explores the next range of reachable indices.
     *
     * ------------------------------------------------------------
     * STEP-BY-STEP:
     * nums = [2,3,1,1,4]
     *
     * i=0 → farthest=2 → jump → currentEnd=2
     * i=1 → farthest=4
     * i=2 → reach currentEnd → jump → currentEnd=4
     *
     * jumps = 2
     *
     * ------------------------------------------------------------
     * TIME COMPLEXITY:
     * O(n) → single pass
     *
     * SPACE COMPLEXITY:
     * O(1) → no extra space used
     *
     */

    public static int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {

            // Update the farthest reachable index
            farthest = Math.max(farthest, i + nums[i]);

            // If we reach the end of current jump range
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }

        return jumps;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 1, 5};
        System.out.println(jump(nums)); // Output: 2
    }
}
