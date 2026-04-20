import java.util.*;

class FrequencySortHeap {

    /*
     * QUESTION:
     * Same as above.
     *
     * -------------------------------------------------------
     * HOW IT WORKS:
     *
     * 1. Count frequency using HashMap.
     *
     * 2. Use a max-heap (priority queue) where:
     *    - Highest frequency comes first.
     *
     * 3. Extract from heap and append characters
     *    based on their frequency.
     *
     * -------------------------------------------------------
     * TIME COMPLEXITY:
     * O(n log k)
     * where k = number of unique characters
     *
     * SPACE COMPLEXITY:
     * O(n)
     */

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> heap =
                new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));

        heap.addAll(map.keySet());

        StringBuilder result = new StringBuilder();

        while (!heap.isEmpty()) {
            char c = heap.poll();
            int freq = map.get(c);

            for (int i = 0; i < freq; i++) {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        FrequencySortHeap obj = new FrequencySortHeap();

        String input = "tree";
        String output = obj.frequencySort(input);

        System.out.println("Input: " + input);
        System.out.println("Output: " + output);
    }
}
