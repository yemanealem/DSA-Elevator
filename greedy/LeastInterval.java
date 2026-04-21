import java.util.*;

public class leastInterval {

    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];

        // Count frequencies
        for (char c : tasks) {
            freq[c - 'A']++;
        }

        // Find maximum frequency
        int maxFreq = 0;
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }

        // Count how many tasks have this max frequency
        int countMax = 0;
        for (int f : freq) {
            if (f == maxFreq) {
                countMax++;
            }
        }

        // Greedy formula
        int part1 = (maxFreq - 1) * (n + 1) + countMax;
        int part2 = tasks.length;

        return Math.max(part1, part2);
    }
}
