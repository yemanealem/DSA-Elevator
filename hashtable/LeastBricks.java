import java.util.*;

class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxEdges = 0;

        for (List<Integer> row : wall) {
            int sum = 0;
            int size = row.size();

            for (int i = 0; i < size - 1; i++) {
                sum += row.get(i);

                int count = map.getOrDefault(sum, 0) + 1;
                map.put(sum, count);

                if (count > maxEdges) {
                    maxEdges = count;
                }
            }
        }

        return wall.size() - maxEdges;
    }
}
