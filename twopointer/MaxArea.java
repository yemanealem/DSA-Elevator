/**
 * LeetCode: Container With Most Water
 *
 * Question:
 * You are given an integer array height of length n.
 * There are n vertical lines drawn such that the two endpoints of the ith line are
 * (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container,
 * such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Note:
 * You may not slant the container.
 *
 * Example:
 * Input:  height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 */

public class MaxArea {

    /**
     * Two Pointer Approach
     *
     * Idea:
     * - Start with two pointers at both ends of the array
     * - The area is limited by the shorter line
     * - Move the pointer pointing to the shorter line
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int maxArea(int[] height) {

        int left = 0;                       // left pointer
        int right = height.length - 1;      // right pointer
        int maxArea = 0;                    // stores maximum water

        while (left < right) {

            int width = right - left;       // distance between lines
            int currentHeight;

            // Find the smaller height
            if (height[left] < height[right]) {
                currentHeight = height[left];
                int area = currentHeight * width;
                if (area > maxArea) {
                    maxArea = area;
                }
                left++;                     // move shorter line
            } else {
                currentHeight = height[right];
                int area = currentHeight * width;
                if (area > maxArea) {
                    maxArea = area;
                }
                right--;                    // move shorter line
            }
        }

        return maxArea;
    }

    /**
     * Main method to test the solution
     */
    public static void main(String[] args) {

        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        int result = maxArea(height);

        System.out.println("Maximum water that can be stored: " + result);
    }
}
