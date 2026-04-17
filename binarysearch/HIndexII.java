public class HIndexII {

    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (citations[mid] >= n - mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return n - left;
    }

    // 🔥 Main method to test the solution
    public static void main(String[] args) {
        HIndexII solution = new HIndexII();

        int[] citations1 = {0, 1, 3, 5, 6};
        int[] citations2 = {1, 2, 100};

        System.out.println("H-Index (Test 1): " + solution.hIndex(citations1)); // Output: 3
        System.out.println("H-Index (Test 2): " + solution.hIndex(citations2)); // Output: 2
    }
}
