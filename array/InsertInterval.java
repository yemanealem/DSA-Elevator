import java.util.*;

/*
🧩 LeetCode 57: Insert Interval

PROBLEM:
You are given an array of non-overlapping intervals sorted by their start time.
You are also given a new interval.

Insert the new interval into the intervals such that:
1. The list remains sorted
2. Overlapping intervals are merged

--------------------------------------------------

EXAMPLE:
Input:
intervals = [[1,3], [6,9]]
newInterval = [2,5]

Output:
[[1,5], [6,9]]

--------------------------------------------------

HOW IT WORKS (GREEDY APPROACH):

We process intervals in 3 steps:

1️⃣ Add all intervals that come BEFORE newInterval
   (i.e., intervals that end before newInterval starts)

2️⃣ Merge all OVERLAPPING intervals
   - Overlap condition:
     intervals[i][0] <= newInterval[1]
   - Update newInterval:
     start = min(start, intervals[i][0])
     end   = max(end, intervals[i][1])

3️⃣ Add remaining intervals AFTER merging

--------------------------------------------------

TIME COMPLEXITY:
O(n) → We traverse the list once

SPACE COMPLEXITY:
O(n) → For storing result

--------------------------------------------------
*/

public class InsertInterval {

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        result.add(newInterval);
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

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
