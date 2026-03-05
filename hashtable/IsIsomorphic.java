class Solution {

    /*
     LeetCode 205 - Isomorphic Strings

     Two strings are isomorphic if:
       - Each character in s maps to exactly one character in t
       - No two characters in s map to the same character in t
       - Order is preserved

     Approach:
     Use two arrays to store mappings:
       mapST[c1] = mapped character in t
       mapTS[c2] = mapped character in s

     If mapping conflicts → return false.

     Time Complexity: O(n)
     Space Complexity: O(1)  (since ASCII size is constant)
    */

    public boolean isIsomorphic(String s, String t) {

        if (s.length() != t.length()) return false;

        int[] mapST = new int[256];
        int[] mapTS = new int[256];

        for (int i = 0; i < s.length(); i++) {

            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // If both characters are not mapped yet
            if (mapST[c1] == 0 && mapTS[c2] == 0) {
                mapST[c1] = c2;
                mapTS[c2] = c1;
            }
            // If already mapped, check consistency
            else {
                if (mapST[c1] != c2) return false;
            }
        }

        return true;
    }
}
