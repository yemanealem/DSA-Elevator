import java.util.*;

public class FindClosestElements {

    // Method to find k closest elements using binary search + sliding window
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k;

        // Binary search for the best starting index
        while (left < right) {
            int mid = left + (right - left) / 2;

            // Compare the distances between x and the edges of the window
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1; // Move right
            } else {
                right = mid; // Move left
            }
        }

        // Collect result
        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    // Main method to test the program
    public static void main(String[] args) {
        FindClosestElements solution = new FindClosestElements();

        int[] arr = {1, 2, 3, 4, 5};
        int k = 4;
        int x = 3;

        List<Integer> result = solution.findClosestElements(arr, k, x);

        System.out.println("The " + k + " closest elements to " + x + " are: " + result);
    }
}
