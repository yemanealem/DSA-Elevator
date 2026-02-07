import java.util.*;

public class FindAllAnagrams {

    /*
     LeetCode 438 – Find All Anagrams in a String

     Idea:
     - Fixed-size sliding window of length p.length()
     - Use one frequency array
     - diff = number of characters still needed
     - When diff == 0 → anagram found
     */

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        int n = s.length(), m = p.length();
        if (n < m) return res;

        int[] freq = new int[26];
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();

        // build frequency from p
        for (char c : pc) {
            freq[c - 'a']++;
        }

        int diff = m;

        for (int i = 0; i < n; i++) {
            // add right character
            if (freq[sc[i] - 'a']-- > 0) diff--;

            // remove left character when window exceeds size
            if (i >= m && freq[sc[i - m] - 'a']++ >= 0) diff++;

            // if all characters matched
            if (diff == 0) {
                res.add(i - m + 1);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        FindAllAnagrams solution = new FindAllAnagrams();

        String s1 = "cbaebabacd";
        String p1 = "abc";
        System.out.println(solution.findAnagrams(s1, p1)); // [0, 6]

        String s2 = "abab";
        String p2 = "ab";
        System.out.println(solution.findAnagrams(s2, p2)); // [0, 1, 2]

        String s3 = "aa";
        String p3 = "bb";
        System.out.println(solution.findAnagrams(s3, p3)); // []
    }
}
