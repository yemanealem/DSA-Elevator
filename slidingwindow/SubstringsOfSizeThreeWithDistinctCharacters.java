public class SubstringsOfSizeThreeWithDistinctCharacters {

    /*
    ============================================================
    LeetCode 1876. Substrings of Size Three with Distinct Characters
    ============================================================

    PROBLEM:
    --------
    Return the number of substrings of length 3 that have
    all distinct characters.

    Example:
    Input:  "aababcabc"
    Output: 4

    Valid substrings:
        "abc", "bca", "cab", "abc"

    ============================================================
    SLIDING WINDOW APPROACH
    ============================================================

    Idea:
    -----
    - Use a frequency array of size 26 (for a-z)
    - Maintain a window of size 3
    - Track how many distinct characters are inside

    Steps:
    ------
    1. Expand window (right pointer)
    2. Add character to frequency
    3. If window size > 3 → shrink from left
    4. If window size == 3 → check distinctness

    Distinct condition:
        All frequency values inside window are 1.

    ============================================================
    RUNNING TIME
    ============================================================

    Let n = s.length()

    We process each character once.

        Time Complexity = O(n)

    ============================================================
    SPACE COMPLEXITY
    ============================================================

        Space Complexity = O(1)

    Because frequency array size is constant (26).

    ============================================================
    */

    public static int countGoodSubstrings(String s) {

        int[] freq = new int[26];
        int left = 0;
        int distinct = 0;
        int result = 0;

        for (int right = 0; right < s.length(); right++) {

            int index = s.charAt(right) - 'a';

            if (freq[index] == 0) distinct++;
            freq[index]++;

            // If window size > 3, shrink from left
            if (right - left + 1 > 3) {
                int leftIndex = s.charAt(left) - 'a';
                freq[leftIndex]--;
                if (freq[leftIndex] == 0) distinct--;
                left++;
            }

            // If window size == 3 and all distinct
            if (right - left + 1 == 3 && distinct == 3) {
                result++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(countGoodSubstrings("xyzzaz"));     // 1
        System.out.println(countGoodSubstrings("aababcabc"));   // 4
    }
}
