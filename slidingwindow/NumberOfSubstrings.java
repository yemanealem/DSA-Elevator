public class NumberOfSubstrings {

    /*
    ============================================================
    LeetCode 1358. Number of Substrings Containing All Three Characters
    ============================================================

    Problem:
    --------
    Given a string s consisting only of characters 'a', 'b', and 'c',
    return the number of substrings containing at least one occurrence
    of all three characters.

    Example:
    Input:  s = "abcabc"
    Output: 10

    ============================================================
    OPTIMIZED APPROACH (Last Seen Index Technique)
    ============================================================

    Idea:
    -----
    Track the last seen index of:
        'a' -> lastA
        'b' -> lastB
        'c' -> lastC

    At every index i:
        The number of valid substrings ending at i is:

            min(lastA, lastB, lastC) + 1

    Why?
    ----
    If the smallest last occurrence is at index min,
    then every substring that starts from index 0 up to min
    and ends at i must contain at least one 'a', 'b', and 'c'.

    ============================================================
    TRACE EXAMPLE: s = "abcabc"
    ============================================================

    i=0, 'a'
    lastA=0, lastB=-1, lastC=-1
    min=-1 → no valid substring

    i=1, 'b'
    lastA=0, lastB=1, lastC=-1
    min=-1 → no valid substring

    i=2, 'c'
    lastA=0, lastB=1, lastC=2
    min=0
    add 0+1 = 1
    total=1

    i=3, 'a'
    lastA=3, lastB=1, lastC=2
    min=1
    add 1+1 = 2
    total=3

    i=4, 'b'
    lastA=3, lastB=4, lastC=2
    min=2
    add 2+1 = 3
    total=6

    i=5, 'c'
    lastA=3, lastB=4, lastC=5
    min=3
    add 3+1 = 4
    total=10

    Final Answer = 10

    ============================================================
    RUNNING TIME ANALYSIS
    ============================================================

    Let n = length of string.

    - We iterate through the string exactly once.
    - Inside the loop we perform:
        * constant number of comparisons
        * constant number of assignments
        * no nested loops
        * no recursion

    Therefore:

        Time Complexity = O(n)

    Because each character is processed exactly one time.

    ============================================================
    SPACE COMPLEXITY
    ============================================================

    We only use:
        lastA, lastB, lastC, result, and loop variable

    No extra arrays or data structures.

        Space Complexity = O(1)

    ============================================================
    This is the most optimal possible solution.
    ============================================================
    */

    public static int numberOfSubstrings(String s) {
        int lastA = -1, lastB = -1, lastC = -1;
        int result = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch == 'a') {
                lastA = i;
            } else if (ch == 'b') {
                lastB = i;
            } else {
                lastC = i;
            }

            int min = lastA < lastB ? lastA : lastB;
            min = min < lastC ? min : lastC;

            if (min >= 0) {
                result += min + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(numberOfSubstrings("abcabc")); // Output: 10
    }
}
