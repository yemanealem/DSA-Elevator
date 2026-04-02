public class JumpGameII {

   

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
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(jump(nums)); // Output: 2
    }
}
