// Question: Group Anagrams
// How it works:
// This program groups words that are anagrams of each other.
// An anagram means words that contain same characters but in different order.
// Example: "eat", "tea", "ate" are anagrams.
//
// Logic:
// For each string, we sort its characters.
// Sorted version becomes the key in HashMap.
// All anagrams will have same sorted key and will be grouped together.
//
// Time Complexity: O(n * k log k) where n = number of strings, k = length of string.

import java.util.*;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);

            String key = String.valueOf(chars);

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        GroupAnagrams ga = new GroupAnagrams();

        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> result = ga.groupAnagrams(input);

        System.out.println(result);
    }
}
