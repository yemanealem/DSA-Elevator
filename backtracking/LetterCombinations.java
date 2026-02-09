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

APPROACH:
- Backtracking
- At each digit, try all possible letters
- Build the combination step by step
- When length == digits.length → save result
*/

public class LetterCombinations {

    public static void main(String[] args) {

        String digits = "23";

        List<String> result = letterCombinations(digits);

        System.out.println("Letter combinations for digits \"" + digits + "\":");
        for (String s : result) {
            System.out.println(s);
        }
    }

    public static List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();

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
    STEP-BY-STEP TRACE for digits = "23"

    Initial call: index=0, path=""

    Level 0 (index=0, digit='2', letters="abc")
    -------------------------------------------
    Choose 'a' → path="a"
      Level 1 (index=1, digit='3', letters="def")
        Choose 'd' → path="ad" → index=2 → SAVE "ad"
        Backtrack → path="a"
        Choose 'e' → path="ae" → SAVE "ae"
        Backtrack → path="a"
        Choose 'f' → path="af" → SAVE "af"
        Backtrack → path="a"
    Backtrack → path=""

    Choose 'b' → path="b"
      Level 1 (index=1, letters="def")
        "bd" → SAVE
        "be" → SAVE
        "bf" → SAVE
    Backtrack → path=""

    Choose 'c' → path="c"
      Level 1 (index=1, letters="def")
        "cd" → SAVE
        "ce" → SAVE
        "cf" → SAVE
    Backtrack → path=""

    END
    Final result: [ad, ae, af, bd, be, bf, cd, ce, cf]

    RECURSION TREE VISUAL:
          ""
       /    |    \
      a     b     c
    / | \  / | \  / | \
   ad ae af bd be bf cd ce cf
    */

    private static void backtrack(String digits, int index,
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
