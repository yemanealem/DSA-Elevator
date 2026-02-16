public class TrapWater {
    class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int totalWater = 0;

        while (left < right) {
            // Determine which side is the "limiting factor"
            if (height[left] < height[right]) {
                // Work on the left side
                if (height[left] >= leftMax) {
                    leftMax = height[left]; // New peak found, no water trapped
                } else {
                    totalWater += leftMax - height[left]; // Water trapped by the left peak
                }
                left++;
            } else {
                // Work on the right side
                if (height[right] >= rightMax) {
                    rightMax = height[right]; // New peak found
                } else {
                    totalWater += rightMax - height[right]; // Water trapped by the right peak
                }
                right--;
            }
        }

        return totalWater;
    }
}
}
