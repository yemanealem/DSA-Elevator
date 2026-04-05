
/*
📌 Problem: Bulls and Cows (LeetCode 299)

🧠 Approach:
- Single pass
- Count array to track digit differences
- Detect bulls and cows efficiently

⏱ Time: O(n)
📦 Space: O(1)
*/

public class BullsAndCows {

    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        int[] count = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';

            if (s == g) {
                bulls++;
            } else {
                // Check if this digit was previously unmatched
                if (count[s] < 0) cows++;
                if (count[g] > 0) cows++;

                count[s]++;
                count[g]--;
            }
        }

        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        BullsAndCows game = new BullsAndCows();

        String secret = "1807";
        String guess = "7810";

        System.out.println(game.getHint(secret, guess));
    }
}
