public class HIndexII {

    public int hIndex(int[] citations) {
        int n = citations.length;

        // Binary search range
        int left = 0, right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // n - mid = number of papers from mid to end
            // If citations[mid] >= number of papers remaining,
            // then this could be a valid H-index position
            if (citations[mid] >= n - mid) {
                // Try to find a smaller index (move left)
                right = mid - 1;
            } else {
                // Not enough citations, move right
                left = mid + 1;
            }
        }

        // left is the first position where condition holds
        // so answer is number of papers from that position
        return n - left;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        HIndexII solution = new HIndexII();

        // Test case 1
        int[] citations1 = {0, 1, 3, 5, 6};
        System.out.println("H-Index Test 1: " + solution.hIndex(citations1));
        // Expected: 3

        // Test case 2
        int[] citations2 = {1, 2, 100};
        System.out.println("H-Index Test 2: " + solution.hIndex(citations2));
        // Expected: 2
    }
}
