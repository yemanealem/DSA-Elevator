
public class HIndex {

    /*
     * LeetCode 274 - H-Index (Optimized O(n))
     *
     * Idea:
     * Use bucket array instead of sorting.
     * Count how many papers have each citation value (capped at n),
     * then find the largest h where total papers >= h.
     *
     * Running Time: O(n)
     */

    public static int hIndex(int[] citations) {

        int n = citations.length;
        int[] bucket = new int[n + 1];

        // Step 1: Fill buckets
        for (int c : citations) {
            if (c >= n) bucket[n]++;
            else bucket[c]++;
        }

        // Step 2: accumulate from right
        int total = 0;

        for (int i = n; i >= 0; i--) {
            total += bucket[i];
            if (total >= i) {
                return i;
            }
        }

        return 0;
    }

    public static void main(String[] args) {

        int[] citations = {3, 0, 6, 1, 5};

        int result = hIndex(citations);

        System.out.println("H-Index: " + result);
    }
}
