import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {

        // If s1 cannot produce s2 at all → early exit
        for (char c : s2.toCharArray()) {
            if (s1.indexOf(c) == -1) return 0;
        }

        int s1Count = 0;
        int s2Count = 0;
        int index = 0;

        // index in s2 → (s1Count, s2Count)
        Map<Integer, int[]> seen = new HashMap<>();

        while (s1Count < n1) {
            s1Count++;

            // match one s1
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == s2.charAt(index)) {
                    index++;
                    if (index == s2.length()) {
                        index = 0;
                        s2Count++;
                    }
                }
            }

            // 🚨 Cycle detection
            if (seen.containsKey(index)) {

                int[] prev = seen.get(index);
                int prevS1 = prev[0];
                int prevS2 = prev[1];

                // cycle length
                int cycleS1 = s1Count - prevS1;
                int cycleS2 = s2Count - prevS2;

                // remaining s1 blocks
                int remaining = n1 - s1Count;

                // number of cycles we can skip
                int times = remaining / cycleS1;

                // fast forward 🚀
                s1Count += times * cycleS1;
                s2Count += times * cycleS2;

            } else {
                seen.put(index, new int[]{s1Count, s2Count});
            }
        }

        return s2Count / n2;
    }
}
