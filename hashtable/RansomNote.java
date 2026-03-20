// Java solution for LeetCode 383: Ransom Note
// Problem: Given two strings ransomNote and magazine, return true if ransomNote can be constructed from letters of magazine.
// Each letter in magazine can only be used once. Return false otherwise.
// Approach: Use a count array of size 26 to store the frequency of each lowercase letter in magazine. 
// Then, iterate through ransomNote and decrement the corresponding counts. 
// If a letter is missing or count is zero, return false. Otherwise, return true after checking all letters.
// Complexity: O(n + m) time, O(1) space (only 26 lowercase letters are stored)

class RansomNote {
    public static boolean canConstruct(String ransomNote, String magazine) {
        // Count of each lowercase letter in magazine
        int[] counts = new int[26]; // for letters 'a' to 'z'
        for (char c : magazine.toCharArray()) {
            counts[c - 'a']++; // increment count for each letter in magazine
        }

        // Check each letter in ransomNote
        for (char c : ransomNote.toCharArray()) {
            if (counts[c - 'a'] == 0) {
                // Letter not available in magazine, cannot construct ransomNote
                return false;
            }
            counts[c - 'a']--; // use the letter by decrementing its count
        }

        return true; // all letters in ransomNote are available in magazine
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
