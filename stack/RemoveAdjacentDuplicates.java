/**
 * LeetCode 1047 - Remove All Adjacent Duplicates In String
 *
 * Problem:
 * Given a string s, repeatedly remove adjacent duplicate characters
 * until no duplicates remain.
 *
 * Example:
 * Input:  "abbaca"
 * Output: "ca"
 *
 * Explanation:
 * abbaca
 *  -> remove "bb" -> aaca
 *  -> remove "aa" -> ca
 *
 * We return the final string after all possible duplicate removals.
 *
 * Approach:
 * We simulate a STACK using a char array.
 *
 * Idea:
 * - Traverse the string from left to right.
 * - Use pointer j as the "top of stack".
 *
 * If current char equals the last inserted char:
 *      pop the stack (j--)
 * Else:
 *      push the char (arr[j++] = arr[i])
 *
 * This efficiently removes adjacent duplicates in one pass.
 *
 * Time Complexity:  O(n)
 * Space Complexity: O(n)
 */

public class RemoveAdjacentDuplicates {

    public static String removeDuplicates(String s) {

        // Convert string to character array for faster operations
        char[] arr = s.toCharArray();

        // j acts as the stack pointer (top of stack)
        int j = 0;

        // Traverse every character
        for (int i = 0; i < arr.length; i++) {

            /*
             If stack is not empty AND
             the current character equals the last inserted character,
             we remove the duplicate by popping the stack.
            */
            if (j > 0 && arr[j - 1] == arr[i]) {
                j--;  // pop
            } 
            else {
                // push character into the "stack"
                arr[j] = arr[i];
                j++;
            }
        }

        /*
         Build the final string from the first j characters
         because they represent the remaining stack content.
        */
        return new String(arr, 0, j);
    }

    public static void main(String[] args) {

        String input = "abbaca";

        String result = removeDuplicates(input);

        System.out.println("Input : " + input);
        System.out.println("Output: " + result);
    }
}
