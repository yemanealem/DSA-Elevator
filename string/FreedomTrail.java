import java.util.*;

public class FreedomTrail {

    public int findRotateSteps(String ring, String key) {
        int n = ring.length();

        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(ring.charAt(i), k -> new ArrayList<>()).add(i);
        }

  
        Map<String, Integer> memo = new HashMap<>();

        return dfs(0, 0, ring, key, map, memo);
    }

    private int dfs(int ringPos, int keyPos, String ring, String key,
                    Map<Character, List<Integer>> map,
                    Map<String, Integer> memo) {

        if (keyPos == key.length()) return 0;

        String state = ringPos + "," + keyPos;
        if (memo.containsKey(state)) return memo.get(state);

        int n = ring.length();
        int result = Integer.MAX_VALUE;

        for (int nextPos : map.get(key.charAt(keyPos))) {
            int diff = Math.abs(nextPos - ringPos);
            int step = Math.min(diff, n - diff);

            int total = step + 1 + dfs(nextPos, keyPos + 1, ring, key, map, memo);
            result = Math.min(result, total);
        }

        memo.put(state, result);
        return result;
    }

    public static void main(String[] args) {
        FreedomTrail sol = new FreedomTrail();
        System.out.println(sol.findRotateSteps("godding", "gd"));
    }
}
