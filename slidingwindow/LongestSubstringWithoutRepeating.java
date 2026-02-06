import java.util.HashMap;

public class LongestSubstringWithoutRepeating {

    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>(); // stores last index of char
        int left = 0;  // left pointer of the window
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // If character is in window, move left pointer
            if (map.containsKey(c)) {
                // Move left to one past last occurrence of c
                left = Math.max(left, map.get(c) + 1);
            }

            map.put(c, right); // update last index of c
            maxLen = Math.max(maxLen, right - left + 1); // update max length
        }

        return maxLen;
    }

    // Test the solution
    public static void main(String[] args) {
        LongestSubstringWithoutRepeating sol = new LongestSubstringWithoutRepeating();

        System.out.println(sol.lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(sol.lengthOfLongestSubstring("bbbbb"));    // 1
        System.out.println(sol.lengthOfLongestSubstring("pwwkew"));   // 3
        System.out.println(sol.lengthOfLongestSubstring(""));         // 0
        System.out.println(sol.lengthOfLongestSubstring("au"));       // 2
    }
}
