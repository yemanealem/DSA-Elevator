/*
QUESTION:
Given a string s, reverse only the vowels of the string and return it.
The vowels are 'a', 'e', 'i', 'o', 'u' (both lowercase and uppercase).

HOW IT WORKS:
- Use two pointers (left and right).
- Move left forward until a vowel is found.
- Move right backward until a vowel is found.
- Swap the vowels.
- Continue until both pointers meet.

RUNNING TIME:
- Time Complexity: O(n) where n is the length of the string
- Space Complexity: O(n) (due to using a char array)
*/

public class ReverseVowels {

    public static String reverseVowels(String s) {
        char[] arr = s.toCharArray();

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {

            while (left < right && !isVowel(arr[left])) {
                left++;
            }

            while (left < right && !isVowel(arr[right])) {
                right--;
            }

            // swap vowels
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }

        return new String(arr);
    }

    private static boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(reverseVowels(s)); // Output: "leotcede"
    }
}
