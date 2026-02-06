class Solution {
    public int characterReplacement(String s, int k) {
         int[] count = new int[26]; 
        int maxCount = 0;          
        int left = 0;              
        int result = 0;            

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            count[c - 'A']++;
            maxCount = Math.max(maxCount, count[c - 'A']);

            // If current window is invalid, shrink from left
            while ((right - left + 1) - maxCount > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            // Update result with the current window size
            result = Math.max(result, right - left + 1);
        }

        return result;
    }
}