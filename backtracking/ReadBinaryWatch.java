import java.util.*;

public class ReadBinaryWatch {

    static class Solution {
        List<String> res = new ArrayList<>();

        int[] hLED = {8, 4, 2, 1};
        int[] mLED = {32, 16, 8, 4, 2, 1};

        public List<String> readBinaryWatch(int turnedOn) {

            // choose how many LEDs go to hours
            for (int hCount = 0; hCount <= Math.min(4, turnedOn); hCount++) {
                int mCount = turnedOn - hCount;
                if (mCount > 6) continue;

                backtrackHour(0, hCount, 0, mCount);
            }
            return res;
        }

        // backtracking for hour LEDs
        private void backtrackHour(int idx, int left, int hour, int mCount) {
            if (hour > 11) return;
            if (left == 0) {
                backtrackMinute(0, mCount, hour, 0);
                return;
            }
            if (idx == 4 || 4 - idx < left) return;

            // choose this hour LED
            backtrackHour(idx + 1, left - 1, hour + hLED[idx], mCount);
            // skip this hour LED
            backtrackHour(idx + 1, left, hour, mCount);
        }

        // backtracking for minute LEDs
        private void backtrackMinute(int idx, int left, int hour, int min) {
            if (min > 59) return;
            if (left == 0) {
                res.add(hour + ":" + (min < 10 ? "0" : "") + min);
                return;
            }
            if (idx == 6 || 6 - idx < left) return;

            // choose this minute LED
            backtrackMinute(idx + 1, left - 1, hour, min + mLED[idx]);
            // skip this minute LED
            backtrackMinute(idx + 1, left, hour, min);
        }
    }

    // ------------------ MAIN METHOD ------------------
    public static void main(String[] args) {
        Solution solution = new Solution();

        int turnedOn = 2;   // try different values
        List<String> result = solution.readBinaryWatch(turnedOn);

        System.out.println("Binary Watch times for turnedOn = " + turnedOn);
        for (String time : result) {
            System.out.println(time);
        }
    }
}
