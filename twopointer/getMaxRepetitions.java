import java.util.HashMap;
import java.util.Map;

public class getMaxRepetitions {

    public int solve(String s1, int n1, String s2, int n2) {

        // If s1 cannot produce s2 at all → early exit
        for (char c : s2.toCharArray()) {
            if (s1.indexOf(c) == -1) return 0;
        }

        int s1Count = 0;
        int s2Count = 0;
        int index = 0;

        Map<Integer, int[]> seen = new HashMap<>();

        while (s1Count < n1) {
            s1Count++;

            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == s2.charAt(index)) {
                    index++;
                    if (index == s2.length()) {
                        index = 0;
                        s2Count++;
                    }
                }
            }

            // cycle detection
            if (seen.containsKey(index)) {

                int[] prev = seen.get(index);
                int prevS1 = prev[0];
                int prevS2 = prev[1];

                int cycleS1 = s1Count - prevS1;
                int cycleS2 = s2Count - prevS2;

                int remaining = n1 - s1Count;
                int times = remaining / cycleS1;

                s1Count += times * cycleS1;
                s2Count += times * cycleS2;

            } else {
                seen.put(index, new int[]{s1Count, s2Count});
            }
        }

        return s2Count / n2;
    }

    // MAIN METHOD inside same class
    public static void main(String[] args) {
        getMaxRepetitions obj = new getMaxRepetitions();

        String s1 = "acb";
        int n1 = 4;
        String s2 = "ab";
        int n2 = 2;

        System.out.println(obj.solve(s1, n1, s2, n2));

        System.out.println(obj.solve("abc", 10, "ac", 2));
    }
}
