import java.util.*;

/*
 * LeetCode 352 - Data Stream as Disjoint Intervals
 *
 * QUESTION:
 * You are given a data stream of non-negative integers.
 * Implement a data structure that supports:
 *   1. addNum(int value): Adds a number to the stream.
 *   2. getIntervals(): Returns the current disjoint intervals.
 *
 * The intervals must be:
 *   - Sorted
 *   - Non-overlapping
 *
 * Example:
 * addNum(1) -> [[1,1]]
 * addNum(3) -> [[1,1],[3,3]]
 * addNum(2) -> [[1,3]]  (merged)
 *
 *
 * HOW IT WORKS (Optimized Approach using TreeMap):
 *
 * We use a TreeMap where:
 *   key = start of interval
 *   value = end of interval
 *
 * For each new number:
 *
 * 1. Find the closest interval on the left (floorKey)
 * 2. Find the closest interval on the right (ceilingKey)
 *
 * Then handle 4 cases:
 *
 * CASE 1: Merge both sides
 *   Example: [1,2] and [4,5], add 3 -> merge into [1,5]
 *
 * CASE 2: Merge with left interval
 *   Example: [1,2], add 3 -> becomes [1,3]
 *
 * CASE 3: Merge with right interval
 *   Example: [4,5], add 3 -> becomes [3,5]
 *
 * CASE 4: No merge
 *   Example: add 10 -> new interval [10,10]
 *
 * Time Complexity:
 *   addNum -> O(log n)
 *   getIntervals -> O(n)
 */

public class SummaryRanges {

    private TreeMap<Integer, Integer> map;

    public SummaryRanges() {
        map = new TreeMap<>();
    }

    public void addNum(int value) {

        // If already covered, do nothing
        if (map.containsKey(value)) return;

        Integer left = map.floorKey(value);     // closest smaller key
        Integer right = map.ceilingKey(value);  // closest larger key

        // CASE 1: Merge both left and right
        if (left != null && right != null &&
                map.get(left) + 1 >= value &&
                right - 1 <= value) {

            map.put(left, map.get(right));
            map.remove(right);
        }

        // CASE 2: Merge with left
        else if (left != null && map.get(left) + 1 >= value) {
            map.put(left, Math.max(map.get(left), value));
        }

        // CASE 3: Merge with right
        else if (right != null && right - 1 <= value) {
            map.put(value, map.get(right));
            map.remove(right);
        }

        // CASE 4: New interval
        else {
            map.put(value, value);
        }
    }

    public int[][] getIntervals() {
        int[][] result = new int[map.size()][2];
        int i = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            result[i][0] = entry.getKey();
            result[i][1] = entry.getValue();
            i++;
        }

        return result;
    }

    // Main method for testing
    public static void main(String[] args) {
        SummaryRanges sr = new SummaryRanges();

        sr.addNum(1);
        print(sr.getIntervals());

        sr.addNum(3);
        print(sr.getIntervals());

        sr.addNum(7);
        print(sr.getIntervals());

        sr.addNum(2);
        print(sr.getIntervals());

        sr.addNum(6);
        print(sr.getIntervals());
    }

    private static void print(int[][] intervals) {
        System.out.print("[");
        for (int[] in : intervals) {
            System.out.print("[" + in[0] + "," + in[1] + "] ");
        }
        System.out.println("]");
    }
}
