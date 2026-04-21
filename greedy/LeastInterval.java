import java.util.*;

/*
===========================================================
LeetCode 621 - Task Scheduler

Problem:
---------
You are given an array of tasks represented by characters
(e.g., ['A','A','A','B','B','B']) and an integer n.

Each task takes 1 unit of time.
The same task must be separated by at least n intervals.

You may insert idle slots if needed.

Return the minimum time required to finish all tasks.

Example:
---------
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A B idle A B idle A B

===========================================================
*/

  /*
    ===========================================================
    Time Complexity:
    ----------------
    O(N) where N = number of tasks

    - Counting frequencies: O(N)
    - Scanning 26 letters: O(1) constant

    Space Complexity:
    ------------------
    O(1) because frequency array is fixed size (26)
    ===========================================================
    */

public class leastInterval {

    public int leastInterval(char[] tasks, int n) {

        int[] freq = new int[26];

        for (char c : tasks) {
            freq[c - 'A']++;
        }

        int maxFreq = 0;
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }

        int countMax = 0;
        for (int f : freq) {
            if (f == maxFreq) {
                countMax++;
            }
        }

    
        int part1 = (maxFreq - 1) * (n + 1) + countMax;

        int part2 = tasks.length;

        return Math.max(part1, part2);
    }

  
}
