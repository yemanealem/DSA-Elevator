/*
Question: Reorganize String

How it works:
- We need to rearrange characters so that no two adjacent characters are the same.
- If it is impossible, return empty string.ss
- Approach:
  1. Count frequency of each character (array of size 26).
  2. Check if max frequency > (n+1)/2 → not possible.
  3. Place most frequent character at even positions first.
  4. Then place remaining characters.
- This ensures no two adjacent characters are the same.

Running Time:
- Time Complexity: O(n)
- Space Complexity: O(n) for result array
*/

class Solution {
    public String reorganizeString(String s) {
        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int max = 0;
        int maxChar = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] > max) {
                max = freq[i];
                maxChar = i;
            }
        }

        if (max > (n + 1) / 2) return "";

        char[] result = new char[n];
        int index = 0;

        // Place most frequent character first at even positions
        while (freq[maxChar] > 0) {
            result[index] = (char) (maxChar + 'a');
            index += 2;
            freq[maxChar]--;
        }

        // Place remaining characters
        for (int i = 0; i < 26; i++) {
            while (freq[i] > 0) {
                if (index >= n) {
                    index = 1; // switch to odd positions
                }

                result[index] = (char) (i + 'a');
                index += 2;
                freq[i]--;
            }
        }

        return new String(result);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.reorganizeString("aab"));   
        System.out.println(sol.reorganizeString("aaab"));  
    }
}
