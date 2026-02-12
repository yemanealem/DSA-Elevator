import java.util.*;

public class FindClosestElements {

    /*
     * LeetCode 658. Find K Closest Elements
     * 
     * Problem:
     * Given a sorted integer array arr, two integers k and x, return the k closest integers
     * to x in the array. The result should also be sorted in ascending order.
     * 
     * An integer a is closer to x than an integer b if:
     * 1. |a - x| < |b - x|, or
     * 2. |a - x| == |b - x| and a < b
     * 
     * Example:
     * arr = [1, 2, 3, 4, 5], k = 4, x = 3
     * Output: [1, 2, 3, 4]
     * 
     * Approach (Binary Search + Sliding Window):
     * - Find the starting index of a window of size k where elements are closest to x
     * - Binary search between 0 and arr.length - k
     * - Compare distances: x - arr[mid] and arr[mid + k] - x
     * - Collect k elements from starting index
     */

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k;

        System.out.println("Binary Search Trace:");
        while (left < right) {
            int mid = left + (right - left) / 2;

            System.out.println("Checking window starting at index " + mid + ": "
                    + Arrays.toString(Arrays.copyOfRange(arr, mid, mid + k)));

            // Compare distances to x
            if (x - arr[mid] > arr[mid + k] - x) {
                System.out.println("Distance on left (" + (x - arr[mid]) + ") > distance on right (" 
                        + (arr[mid + k] - x) + "), move right");
                left = mid + 1;
            } else {
                System.out.println("Distance on left (" + (x - arr[mid]) + ") <= distance on right (" 
                        + (arr[mid + k] - x) + "), move left");
                right = mid;
            }
        }

        System.out.println("Final window starts at index: " + left);
        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        FindClosestElements solution = new FindClosestElements();

        int[] arr = {1, 2, 3, 4, 5};
        int k = 4;
        int x = 3;

        List<Integer> result = solution.findClosestElements(arr, k, x);

        System.out.println("\nThe " + k + " closest elements to " + x + " are: " + result);
    }
}
