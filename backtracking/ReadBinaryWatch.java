import java.util.*;

/*
================================================================================
LeetCode 401 – Binary Watch

QUESTION:
A binary watch has 10 LEDs:
- 4 LEDs represent HOURS:  [8, 4, 2, 1]  -> max hour = 11
- 6 LEDs represent MINUTES:[32,16,8,4,2,1] -> max minute = 59

Given an integer turnedOn (number of LEDs that are ON),
return all possible times the watch could represent.

Example:
Input: turnedOn = 1
Output: ["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]


APPROACH (OPTIMIZED BACKTRACKING):
Instead of backtracking over all 10 LEDs together (slow),
we SPLIT the problem into two independent backtracking steps:

1) Decide how many LEDs go to HOURS (0–4)
2) Remaining LEDs go to MINUTES (0–6)
3) Backtrack hours and minutes separately
4) Prune early if hour > 11 or minute > 59

This drastically reduces the recursion tree and is the
FASTEST possible solution using BACKTRACKING only.


HOW IT WORKS – TRACE (turnedOn = 2):

Step 1: Distribute LEDs
--------------------------------
hCount = 0 → mCount = 2
hCount = 1 → mCount = 1
hCount = 2 → mCount = 0

Step 2: Backtrack HOURS, then MINUTES

Case 1: hCount = 1, mCount = 1
--------------------------------
Hour LEDs chosen:
- pick 1  → hour = 1
- pick 2  → hour = 2
- pick 4  → hour = 4
- pick 8  → hour = 8

For each hour, backtrack minutes with 1 LED:
- pick 1  → min = 1
- pick 2  → min = 2
- pick 4  → min = 4
...
Valid results:
1:01, 1:02, 1:04, ...
2:01, 2:02, ...
4:01, ...
8:01, ...

Case 2: hCount = 2, mCount = 0
--------------------------------
Hour combinations:
1 + 2 = 3
1 + 4 = 5
2 + 4 = 6
1 + 8 = 9
2 + 8 = 10

Minutes = 0
Results:
3:00, 5:00, 6:00, 9:00, 10:00

All invalid paths are pruned early:
- hour > 11 ❌
- minute > 59 ❌
- not enough LEDs left ❌


WHY THIS IS OPTIMAL BACKTRACKING:
- No recursion over all 10 LEDs
- Smaller recursion depth
- Strong pruning
- Clean separation of concerns
- Typically beats ~88–92% on LeetCode

================================================================================
*/

public class ReadBinaryWatch {

    static class Solution {
        List<String> res = new ArrayList<>();

        int[] hLED = {8, 4, 2, 1};
        int[] mLED = {32, 16, 8, 4, 2, 1};

        public List<String> readBinaryWatch(int turnedOn) {

            // decide how many LEDs go to hours
            for (int hCount = 0; hCount <= Math.min(4, turnedOn); hCount++) {
                int mCount = turnedOn - hCount;
                if (mCount > 6) continue;

                backtrackHour(0, hCount, 0, mCount);
            }
            return res;
        }

        // backtracking hour LEDs
        private void backtrackHour(int idx, int left, int hour, int mCount) {
            if (hour > 11) return;
            if (left == 0) {
                backtrackMinute(0, mCount, hour, 0);
                return;
            }
            if (idx == 4 || 4 - idx < left) return;

            backtrackHour(idx + 1, left - 1, hour + hLED[idx], mCount);
            backtrackHour(idx + 1, left, hour, mCount);
        }

        // backtracking minute LEDs
        private void backtrackMinute(int idx, int left, int hour, int min) {
            if (min > 59) return;
            if (left == 0) {
                res.add(hour + ":" + (min < 10 ? "0" : "") + min);
                return;
            }
            if (idx == 6 || 6 - idx < left) return;

            backtrackMinute(idx + 1, left - 1, hour, min + mLED[idx]);
            backtrackMinute(idx + 1, left, hour, min);
        }
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        Solution solution = new Solution();

        int turnedOn = 2;
        List<String> times = solution.readBinaryWatch(turnedOn);

        System.out.println("Binary Watch times for turnedOn = " + turnedOn);
        for (String t : times) {
            System.out.println(t);
        }
    }
}
