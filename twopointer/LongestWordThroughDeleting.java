import java.util.*;

class Solution {

    public String findLongestWord(String s, List<String> dictionary) {

        Map<Character, List<Integer>> map = new HashMap<>();

        // Step 1: Build index map
        for (int i = 0; i < s.length(); i++) {
            map.computeIfAbsent(s.charAt(i), k -> new ArrayList<>()).add(i);
        }

        // Step 2: Sort dictionary
        Collections.sort(dictionary, (a, b) -> {
            if (a.length() != b.length()) {
                return b.length() - a.length();
            }
            return a.compareTo(b);
        });

        // Step 3: Check each word
        for (String word : dictionary) {
            if (isSubsequence(word, map)) {
                return word;
            }
        }

        return "";
    }

    // Check if word is subsequence using binary search
    private boolean isSubsequence(String word, Map<Character, List<Integer>> map) {
        int prevIndex = -1;

        for (char c : word.toCharArray()) {
            if (!map.containsKey(c)) return false;

            List<Integer> indices = map.get(c);

            int pos = findNextIndex(indices, prevIndex);

            if (pos == -1) return false;

            prevIndex = indices.get(pos);
        }

        return true;
    }

    // Binary search helper
    private int findNextIndex(List<Integer> indices, int target) {
        int left = 0, right = indices.size() - 1;
        int result = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (indices.get(mid) > target) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }
}
