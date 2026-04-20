import java.util.*;

class FrequencySortBucket {

    /*
     * QUESTION:
     * Given a string s, sort it in decreasing order based on the frequency of characters.
     * Return the sorted string.
     *
     * Example:
     * Input: "tree"
     * Output: "eert" or "eetr"
     *
     * -------------------------------------------------------
     * HOW IT WORKS:
     *
     * 1. Count frequency of each character using a HashMap.
     *    Example: "tree" → {t:1, r:1, e:2}
     *
     * 2. Create "buckets" where index = frequency.
     *    bucket[2] → ['e']
     *    bucket[1] → ['t', 'r']
     *
     * 3. Traverse buckets from highest frequency to lowest.
     *    Append characters multiple times based on frequency.
     *
     * 4. Build the final string.
     *
     * -------------------------------------------------------
     * TIME COMPLEXITY:
     * O(n) → counting + building result
     *
     * SPACE COMPLEXITY:
     * O(n) → map + buckets
     */

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();

        // Step 1: Count frequency
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Step 2: Create buckets
        List<Character>[] buckets = new List[s.length() + 1];

        for (char c : map.keySet()) {
            int freq = map.get(c);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(c);
        }

        // Step 3: Build result
        StringBuilder result = new StringBuilder();

        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] != null) {
                for (char c : buckets[i]) {
                    for (int j = 0; j < i; j++) {
                        result.append(c);
                    }
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        FrequencySortBucket obj = new FrequencySortBucket();

        String input = "tree";
        String output = obj.frequencySort(input);

        System.out.println("Input: " + input);
        System.out.println("Output: " + output);
    }
}
