/**
 * LeetCode 2390 - Removing Stars From a String
 *
 * Problem:
 * Given a string containing letters and '*',
 * each '*' removes the closest character to its left.
 *
 * Example:
 * Input:  "leet**cod*e"
 * Output: "lecoe"
 *
 * Approach:
 * Use a stack simulation with a char array.
 *
 * If current char is '*'
 *      pop the stack
 * else
 *      push the character
 *
 * Time Complexity:  O(n)
 * Space Complexity: O(n)
 */

public class RemovingStarsFromString {

    public static String removeStars(String s) {

        char[] arr = s.toCharArray();
        int j = 0; // stack pointer

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == '*') {
                j--; // pop previous character
            } 
            else {
                arr[j] = arr[i]; // push character
                j++;
            }
        }

        return new String(arr, 0, j);
    }

    public static void main(String[] args) {

        String s = "leet**cod*e";

        String result = removeStars(s);

        System.out.println("Input : " + s);
        System.out.println("Output: " + result);
    }
}
