class Solution {
    public int characterReplacement(String s, int k) {
         int[] count = new int[26];  // Frequency array for 'A'-'Z'
        int left = 0;               // Left pointer of the window
        int maxCount = 0;           // Max frequency in current window
        int maxLen = 0;             // Result

        for (int right = 0; right < s.length(); right++) {
            int idx = s.charAt(right) - 'A';
            count[idx]++;
            maxCount = Math.max(maxCount, count[idx]); // Update max char frequency

            // Shrink window if replacements needed exceed k
            while ((right - left + 1) - maxCount > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1); // Update max length
        }

        return maxLen;
    }
}