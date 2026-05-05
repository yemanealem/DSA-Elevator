import java.util.*;

/*
    LeetCode: The Skyline Problem

    Problem:
    Given a list of buildings represented as [left, right, height],
    return the skyline formed by these buildings as a list of key points
    where the height changes.

    How it works:
    - Convert each building into two events:
        (left, -height)  -> building start
        (right, height)  -> building end
    - Sort all events:
        - by x-coordinate
        - if tie: start events before end events (using negative height)
    - Use a TreeMap as a multiset to store active heights:
        - key = height
        - value = count
    - Traverse events:
        - if start → add height
        - if end → remove height
    - Track current max height using TreeMap.lastKey()
    - If max height changes → add a skyline point

    Time Complexity:
    O(N log N)
    - Sorting events: O(N log N)
    - Each insert/remove in TreeMap: O(log N)
*/

public class SkylineProblem {

    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> events = new ArrayList<>();

        for (int[] b : buildings) {
            events.add(new int[]{b[0], -b[2]});
            events.add(new int[]{b[1], b[2]});
        }

        Collections.sort(events, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1);

        int prevMax = 0;
        List<List<Integer>> result = new ArrayList<>();

        for (int[] e : events) {
            int x = e[0];
            int h = e[1];

            if (h < 0) {
                map.put(-h, map.getOrDefault(-h, 0) + 1);
            } else {
                map.put(h, map.get(h) - 1);
                if (map.get(h) == 0) {
                    map.remove(h);
                }
            }

            int currMax = map.lastKey();

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