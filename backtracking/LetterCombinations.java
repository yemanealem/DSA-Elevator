import java.util.ArrayList;
import java.util.List;

/*
QUESTION (LeetCode 17 – Letter Combinations of a Phone Number):

Given a string containing digits from 2–9 inclusive,
return all possible letter combinations that the number could represent.

Example:
Input: "23"
Output:
[ad, ae, af, bd, be, bf, cd, ce, cf]
*/

public class LetterCombinations {

    public static void main(String[] args) {

        String digits = "23";

        Solution solution = new Solution();
        List<String> result = solution.letterCombinations(digits);

        // Print results
        for (String s : result) {
            System.out.println(s);
        }
    }
}

// LeetCode-style Solution class
class Solution {

    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();

        // Edge case
        if (digits == null || digits.length() == 0) {
            return result;
        }

        // Phone keypad mapping (index = digit)
        String[] map = {
            "",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
        };

        backtrack(digits, 0, new StringBuilder(), result, map);
        return result;
    }

    /*
    TRACE for digits = "23"

    index=0 → digit '2' → "abc"
      choose 'a'
        index=1 → digit '3' → "def"
          ad ✔ ae ✔ af ✔
      choose 'b'
          bd ✔ be ✔ bf ✔
      choose 'c'
          cd ✔ ce ✔ cf ✔
    */

    private void backtrack(String digits, int index,
                           StringBuilder path,
                           List<String> result,
                           String[] map) {

        // Base case: full combination formed
        if (index == digits.length()) {
            result.add(path.toString());
            return;
        }

        String letters = map[digits.charAt(index) - '0'];

        for (char ch : letters.toCharArray()) {

            // 1️⃣ choose
            path.append(ch);

            // 2️⃣ explore
            backtrack(digits, index + 1, path, result, map);

            // 3️⃣ un-choose (backtrack)
            path.deleteCharAt(path.length() - 1);
        }
    }
}
