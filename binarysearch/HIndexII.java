/*
==================== PROBLEM ====================
Given a sorted array citations (ascending), compute the H-Index.

H-Index is the maximum value h such that:
- The researcher has at least h papers
- Each of those papers has at least h citations

=================================================
HOW IT WORKS (INTUITION)
=================================================
We use Binary Search because the array is sorted.

At any index i:
- citations[i] represents citations of a paper
- n - i represents how many papers are from i to end

If citations[i] >= (n - i):
    → It means we might have found a valid H-index region

We try to find the FIRST index where this condition becomes true.
Then:
    answer = n - that_index

=================================================
TIME COMPLEXITY
=================================================
Time Complexity: O(log n)   (Binary Search)
Space Complexity: O(1)
=================================================
*/

public class HIndexII {

    public int hIndex(int[] citations) {
        int n = citations.length;

        int left = 0, right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // If this position can form a valid H-index
            if (citations[mid] >= n - mid) {
                right = mid - 1; // try to find earlier valid index
            } else {
                left = mid + 1; // move right
            }
        }

        // left is first valid position
        return n - left;
    }

    public static void main(String[] args) {
        HIndexII solution = new HIndexII();

        int[] citations1 = {0, 1, 3, 5, 6};
        int[] citations2 = {1, 2, 100};

        System.out.println(solution.hIndex(citations1)); // 3
        System.out.println(solution.hIndex(citations2)); // 2
    }
}
