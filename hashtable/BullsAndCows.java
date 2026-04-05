
/*
📌 Problem: Bulls and Cows (LeetCode 299)

Given two strings secret and guess, return a hint:
"xAyB"
- A = bulls (correct digit, correct position)
- B = cows (correct digit, wrong position)

------------------------------------------------------------

🧠 Approach: Hash Table (Count Array)

We use:
- bulls counter
- cows counter
- count[10] array to track digit differences

🔹 For each index i:
1. If secret[i] == guess[i] → bulls++
2. Else:
   - If count[secret[i]] < 0 → cow++
   - If count[guess[i]] > 0 → cow++
   - Then:
        count[secret[i]]++
        count[guess[i]]--

👉 This balances unmatched digits and detects cows efficiently.

------------------------------------------------------------

⏱ Time Complexity:
- O(n)

📦 Space Complexity:
- O(1) → fixed array of size 10

------------------------------------------------------------

✅ Example:
secret = "1807"
guess  = "7810"

Output: "1A3B"
*/

public class BullsAndCows {

    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        int[] count = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);

            if (s == g) {
                bulls++;
            } else {
                // If previously seen in guess
                if (count[s - '0'] < 0) cows++;
                // If previously seen in secret
                if (count[g - '0'] > 0) cows++;

                count[s - '0']++;
                count[g - '0']--;
            }
        }

        return bulls + "A" + cows + "B";
    }

    // 🔹 Main method for testing
    public static void main(String[] args) {
        BullsAndCows game = new BullsAndCows();

        String secret = "1807";
        String guess = "7810";

        System.out.println("Secret: " + secret);
        System.out.println("Guess:  " + guess);

        String result = game.getHint(secret, guess);

        System.out.println("Hint: " + result);
    }
}
