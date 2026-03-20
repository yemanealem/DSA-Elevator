// Java solution for LeetCode "Ransom Note" problem
// Complexity: O(n + m) time, O(1) space (constant 26 letters)

class RansomNote {
    public static boolean canConstruct(String ransomNote, String magazine) {
        // Count letters in magazine
        int[] counts = new int[26]; // for lowercase letters 'a' to 'z'

        for (char c : magazine.toCharArray()) {
            counts[c - 'a']++;
        }

        // Check each letter in ransomNote
        for (char c : ransomNote.toCharArray()) {
            if (counts[c - 'a'] == 0) {
                // Letter not available in magazine
                return false;
            }
            counts[c - 'a']--; // use the letter
        }

        return true;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        String ransomNote1 = "aa";
        String magazine1 = "aab";
        System.out.println(canConstruct(ransomNote1, magazine1)); // true

        String ransomNote2 = "aa";
        String magazine2 = "ab";
        System.out.println(canConstruct(ransomNote2, magazine2)); // false
    }
}
