import java.util.*;

public class SkylineProblemOptimized {

    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> events = new ArrayList<>();

        // Step 1: Create events
        for (int[] b : buildings) {
            events.add(new int[]{b[0], -b[2]}); // start
            events.add(new int[]{b[1], b[2]});  // end
        }

        // Step 2: Sort events
        Collections.sort(events, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        // TreeMap as max heap (multiset)
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1); // ground

        int prevMax = 0;
        List<List<Integer>> result = new ArrayList<>();

        // Step 3: Process events
        for (int[] e : events) {
            int x = e[0];
            int h = e[1];

            if (h < 0) {
                // start event → add height
                map.put(-h, map.getOrDefault(-h, 0) + 1);
            } else {
                // end event → remove height
                map.put(h, map.get(h) - 1);
                if (map.get(h) == 0) {
                    map.remove(h);
                }
            }

            int currMax = map.lastKey(); // max height

            if (currMax != prevMax) {
                result.add(Arrays.asList(x, currMax));
                prevMax = currMax;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] buildings = {
            {2, 9, 10},
            {3, 7, 15},
            {5, 12, 12},
            {15, 20, 10},
            {19, 24, 8}
        };

        List<List<Integer>> skyline = getSkyline(buildings);

        for (List<Integer> point : skyline) {
            System.out.println(point);
        }
    }
}