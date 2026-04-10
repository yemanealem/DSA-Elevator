import java.util.*;

public class InsertInterval {

    // Function to insert and merge intervals
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // 1. Add all intervals before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // 2. Merge overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        result.add(newInterval);

        // 3. Add remaining intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    // Helper function to print intervals
    public static void printIntervals(int[][] intervals) {
        for (int[] interval : intervals) {
            System.out.print("[" + interval[0] + "," + interval[1] + "] ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] intervals = {
            {1, 3},
            {6, 9}
        };

        int[] newInterval = {2, 5};

        System.out.println("Original Intervals:");
        printIntervals(intervals);

        System.out.println("New Interval: [" + newInterval[0] + "," + newInterval[1] + "]");

        int[][] result = insert(intervals, newInterval);

        System.out.println("After Insertion:");
        printIntervals(result);
    }
}
