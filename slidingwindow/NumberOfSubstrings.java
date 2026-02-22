class Solution {
    public int numberOfSubstrings(String s) {
        int lastA = -1, lastB = -1, lastC = -1;
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == 'a') lastA = i;
            else if (ch == 'b') lastB = i;
            else lastC = i;

            int minIndex = Math.min(lastA, Math.min(lastB, lastC));

            if (minIndex != -1) {
                result += minIndex + 1;
            }
        }

        return result;
    }
}