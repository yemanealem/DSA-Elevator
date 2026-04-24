import java.util.*;

public class HIndex {

    /*
     * LeetCode 274 - H-Index
     *
     * How it works:
     * Sort citations in descending order, then find the maximum index i
     * such that citations[i] >= i + 1.
     *
     * Running Time: O(n log n)
     */

    public static int hIndex(int[] citations) {

        Arrays.sort(citations);
        int n = citations.length;

        for (int i = 0; i < n; i++) {
            int h = n - i;
            if (citations[i] >= h) {
                return h;
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
